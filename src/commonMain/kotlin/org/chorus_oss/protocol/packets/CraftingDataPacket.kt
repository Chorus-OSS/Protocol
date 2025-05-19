package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.types.recipe.MaterialReducer
import org.chorus_oss.protocol.types.recipe.PotionContainerChangeRecipe
import org.chorus_oss.protocol.types.recipe.PotionRecipe
import org.chorus_oss.protocol.types.recipe.Recipe

data class CraftingDataPacket(
    val recipes: List<Recipe>,
    val potionRecipes: List<PotionRecipe>,
    val potionContainerChangeRecipes: List<PotionContainerChangeRecipe>,
    val materialReducers: List<MaterialReducer>,
    val clearRecipes: Boolean,
) : Packet(id) {
    companion object : PacketCodec<CraftingDataPacket> {
        override val id: Int
            get() = ProtocolInfo.CRAFTING_DATA_PACKET

        override fun serialize(value: CraftingDataPacket, stream: Sink) {
            ProtoHelper.serializeList(value.recipes, stream, Recipe)
            ProtoHelper.serializeList(value.potionRecipes, stream, PotionRecipe)
            ProtoHelper.serializeList(value.potionContainerChangeRecipes, stream, PotionContainerChangeRecipe)
            ProtoHelper.serializeList(value.materialReducers, stream, MaterialReducer)
            Proto.Boolean.serialize(value.clearRecipes, stream)
        }

        override fun deserialize(stream: Source): CraftingDataPacket {
            return CraftingDataPacket(
                recipes = ProtoHelper.deserializeList(stream, Recipe),
                potionRecipes = ProtoHelper.deserializeList(stream, PotionRecipe),
                potionContainerChangeRecipes = ProtoHelper.deserializeList(stream, PotionContainerChangeRecipe),
                materialReducers = ProtoHelper.deserializeList(stream, MaterialReducer),
                clearRecipes = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
