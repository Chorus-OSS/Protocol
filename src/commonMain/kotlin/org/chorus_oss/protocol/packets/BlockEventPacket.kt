package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos

data class BlockEventPacket(
    val blockPosition: BlockPos,
    val eventType: Int,
    val eventValue: Int,
) : Packet(id) {
    companion object : PacketCodec<BlockEventPacket> {
        override val id: Int = 26

        override fun deserialize(stream: Source): BlockEventPacket {
            return BlockEventPacket(
                blockPosition = NetBlockPos.deserialize(stream),
                eventType = ProtoVAR.Int.deserialize(stream),
                eventValue = ProtoVAR.Int.deserialize(stream),
            )
        }

        override fun serialize(value: BlockEventPacket, stream: Sink) {
            NetBlockPos.serialize(value.blockPosition, stream)
            ProtoVAR.Int.serialize(value.eventType, stream)
            ProtoVAR.Int.serialize(value.eventValue, stream)
        }
    }
}
