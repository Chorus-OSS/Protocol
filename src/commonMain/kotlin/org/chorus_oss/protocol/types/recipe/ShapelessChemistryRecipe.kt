package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.Uuid
import org.chorus_oss.protocol.types.item.ItemInstance
import org.chorus_oss.protocol.types.item.desciptor.ItemDescriptorCount
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ShapelessChemistryRecipe(
    val recipeID: String,
    val input: List<ItemDescriptorCount>,
    val output: List<ItemInstance>,
    val uuid: Uuid,
    val block: String,
    val priority: Int,
    val unlockingRequirement: RecipeUnlockingRequirement,
    val recipeNetworkID: UInt,
) : Recipe() {
    override val type: Recipe.Companion.Type
        get() = Recipe.Companion.Type.ShapelessChemistry

    companion object : ProtoCodec<ShapelessChemistryRecipe> {
        override fun serialize(value: ShapelessChemistryRecipe, stream: Sink) {
            Proto.String.serialize(value.recipeID, stream)
            ProtoHelper.serializeList(value.input, stream, ItemDescriptorCount)
            ProtoHelper.serializeList(value.output, stream, ItemInstance)
            Proto.Uuid.serialize(value.uuid, stream)
            Proto.String.serialize(value.block, stream)
            ProtoVAR.Int.serialize(value.priority, stream)
            RecipeUnlockingRequirement.serialize(value.unlockingRequirement, stream)
            ProtoVAR.UInt.serialize(value.recipeNetworkID, stream)
        }

        override fun deserialize(stream: Source): ShapelessChemistryRecipe {
            return ShapelessChemistryRecipe(
                recipeID = Proto.String.deserialize(stream),
                input = ProtoHelper.deserializeList(stream, ItemDescriptorCount),
                output = ProtoHelper.deserializeList(stream, ItemInstance),
                uuid = Proto.Uuid.deserialize(stream),
                block = Proto.String.deserialize(stream),
                priority = ProtoVAR.Int.deserialize(stream),
                unlockingRequirement = RecipeUnlockingRequirement.deserialize(stream),
                recipeNetworkID = ProtoVAR.UInt.deserialize(stream),
            )
        }
    }
}
