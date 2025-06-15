package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.UInt

data class CraftRecipeRequestAction(
    val recipeNetworkId: UInt,
    val numberOfCrafts: Byte,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CraftRecipe

    companion object : ProtoCodec<CraftRecipeRequestAction> {
        override fun serialize(
            value: CraftRecipeRequestAction,
            stream: Sink
        ) {
            ProtoVAR.UInt.serialize(value.recipeNetworkId, stream)
            Proto.Byte.serialize(value.numberOfCrafts, stream)
        }

        override fun deserialize(stream: Source): CraftRecipeRequestAction {
            return CraftRecipeRequestAction(
                recipeNetworkId = ProtoVAR.UInt.deserialize(stream),
                numberOfCrafts = Proto.Byte.deserialize(stream),
            )
        }
    }
}
