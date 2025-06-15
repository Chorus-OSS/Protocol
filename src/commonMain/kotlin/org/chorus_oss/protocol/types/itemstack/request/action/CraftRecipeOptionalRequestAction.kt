package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt

data class CraftRecipeOptionalRequestAction(
    val recipeNetworkId: UInt,
    val filteredStringIndex: Int,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CraftRecipeOptional

    companion object : ProtoCodec<CraftRecipeOptionalRequestAction> {
        override fun serialize(
            value: CraftRecipeOptionalRequestAction,
            stream: Sink
        ) {
            ProtoVAR.UInt.serialize(value.recipeNetworkId, stream)
            ProtoLE.Int.serialize(value.filteredStringIndex, stream)
        }

        override fun deserialize(stream: Source): CraftRecipeOptionalRequestAction {
            return CraftRecipeOptionalRequestAction(
                recipeNetworkId = ProtoVAR.UInt.deserialize(stream),
                filteredStringIndex = ProtoLE.Int.deserialize(stream),
            )
        }
    }
}
