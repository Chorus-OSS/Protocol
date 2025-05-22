package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3

data class BlockEventPacket(
    val blockPosition: IVector3,
    val eventType: Int,
    val eventValue: Int,
) : Packet(id) {
    companion object : PacketCodec<BlockEventPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.BLOCK_EVENT_PACKET

        override fun deserialize(stream: Source): BlockEventPacket {
            return BlockEventPacket(
                blockPosition = UIVector3.deserialize(stream),
                eventType = ProtoVAR.Int.deserialize(stream),
                eventValue = ProtoVAR.Int.deserialize(stream),
            )
        }

        override fun serialize(value: BlockEventPacket, stream: Sink) {
            UIVector3.serialize(value.blockPosition, stream)
            ProtoVAR.Int.serialize(value.eventType, stream)
            ProtoVAR.Int.serialize(value.eventValue, stream)
        }
    }
}
