package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class MineBlockRequestAction(
    val hotbarSlot: Int,
    val predictedDurability: Int,
    val stackNetworkID: Int,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.MineBlock

    companion object : ProtoCodec<MineBlockRequestAction> {
        override fun serialize(
            value: MineBlockRequestAction,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.hotbarSlot, stream)
            ProtoVAR.Int.serialize(value.predictedDurability, stream)
            ProtoVAR.Int.serialize(value.stackNetworkID, stream)
        }

        override fun deserialize(stream: Source): MineBlockRequestAction {
            return MineBlockRequestAction(
                hotbarSlot = ProtoVAR.Int.deserialize(stream),
                predictedDurability = ProtoVAR.Int.deserialize(stream),
                stackNetworkID = ProtoVAR.Int.deserialize(stream)
            )
        }
    }
}
