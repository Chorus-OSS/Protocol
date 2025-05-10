package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.shared.types.IVector3
import org.chorus_oss.protocol.shared.types.UIVector3

data class BlockEventPacket(
    val blockPosition: IVector3,
    val eventType: Int,
    val eventValue: Int,
) {
    companion object : PacketCodec<BlockEventPacket> {
        override val id: Int
            get() = ProtocolInfo.BLOCK_EVENT_PACKET

        override fun deserialize(stream: Buffer): BlockEventPacket {
            return BlockEventPacket(
                blockPosition = UIVector3.deserialize(stream),
                eventType = ProtoVAR.Int.deserialize(stream),
                eventValue = ProtoVAR.Int.deserialize(stream),
            )
        }

        override fun serialize(value: BlockEventPacket, stream: Buffer) {
            UIVector3.serialize(value.blockPosition, stream)
            ProtoVAR.Int.serialize(value.eventType, stream)
            ProtoVAR.Int.serialize(value.eventValue, stream)
        }
    }
}
