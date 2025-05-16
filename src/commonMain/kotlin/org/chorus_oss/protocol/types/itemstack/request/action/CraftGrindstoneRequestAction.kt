package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt

data class CraftGrindstoneRequestAction(
    val recipeNetworkID: UInt,
    val numberOfCrafts: Byte,
    val cost: Int,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CRAFT_REPAIR_AND_DISENCHANT

    companion object : ProtoCodec<CraftGrindstoneRequestAction> {
        override fun serialize(
            value: CraftGrindstoneRequestAction,
            stream: Sink
        ) {
            ProtoVAR.UInt.serialize(value.recipeNetworkID, stream)
            Proto.Byte.serialize(value.numberOfCrafts, stream)
            ProtoVAR.Int.serialize(value.cost, stream)
        }

        override fun deserialize(stream: Source): CraftGrindstoneRequestAction {
            return CraftGrindstoneRequestAction(
                recipeNetworkID = ProtoVAR.UInt.deserialize(stream),
                numberOfCrafts = Proto.Byte.deserialize(stream),
                cost = ProtoVAR.Int.deserialize(stream)
            )
        }
    }
}