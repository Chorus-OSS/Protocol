package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.item.desciptor.ItemDescriptorCount

data class AutoCraftRecipeRequestAction(
    val recipeNetworkId: UInt,
    val numberOfCrafts: Byte,
    val timesCrafted: Byte,
    val ingredients: List<ItemDescriptorCount>,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CRAFT_RECIPE_AUTO

    companion object : ProtoCodec<AutoCraftRecipeRequestAction> {
        override fun serialize(
            value: AutoCraftRecipeRequestAction,
            stream: Sink
        ) {
            ProtoVAR.UInt.serialize(value.recipeNetworkId, stream)
            Proto.Byte.serialize(value.numberOfCrafts, stream)
            Proto.Byte.serialize(value.timesCrafted, stream)
            ProtoHelper.serializeList(value.ingredients, stream, ItemDescriptorCount::serialize)
        }

        override fun deserialize(stream: Source): AutoCraftRecipeRequestAction {
            return AutoCraftRecipeRequestAction(
                recipeNetworkId = ProtoVAR.UInt.deserialize(stream),
                numberOfCrafts = Proto.Byte.deserialize(stream),
                timesCrafted = Proto.Byte.deserialize(stream),
                ingredients = ProtoHelper.deserializeList(stream, ItemDescriptorCount::deserialize)
            )
        }
    }
}
