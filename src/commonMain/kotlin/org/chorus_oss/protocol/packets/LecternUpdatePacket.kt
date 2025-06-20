package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos


data class LecternUpdatePacket(
    val page: Byte,
    val totalPages: Byte,
    val blockPosition: BlockPos,
) : Packet(id) {
    companion object : PacketCodec<LecternUpdatePacket> {
        override val id: Int = 125

        override fun serialize(value: LecternUpdatePacket, stream: Sink) {
            Proto.Byte.serialize(value.page, stream)
            Proto.Byte.serialize(value.totalPages, stream)
            NetBlockPos.serialize(value.blockPosition, stream)
        }

        override fun deserialize(stream: Source): LecternUpdatePacket {
            return LecternUpdatePacket(
                page = Proto.Byte.deserialize(stream),
                totalPages = Proto.Byte.deserialize(stream),
                blockPosition = NetBlockPos.deserialize(stream)
            )
        }
    }
}
