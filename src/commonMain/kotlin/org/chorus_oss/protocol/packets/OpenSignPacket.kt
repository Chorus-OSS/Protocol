package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos

data class OpenSignPacket(
    val position: BlockPos,
    val frontSide: Boolean,
) : Packet(id) {
    companion object : PacketCodec<OpenSignPacket> {
        override val id: Int = 303

        override fun serialize(value: OpenSignPacket, stream: Sink) {
            NetBlockPos.serialize(value.position, stream)
            Proto.Boolean.serialize(value.frontSide, stream)
        }

        override fun deserialize(stream: Source): OpenSignPacket {
            return OpenSignPacket(
                position = NetBlockPos.deserialize(stream),
                frontSide = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
