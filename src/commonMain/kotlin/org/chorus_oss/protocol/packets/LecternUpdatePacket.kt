package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3


data class LecternUpdatePacket(
    val page: Byte,
    val totalPages: Byte,
    val blockPosition: IVector3,
) : Packet(id) {
    companion object : PacketCodec<LecternUpdatePacket> {
        override val id: Int
            get() = ProtocolInfo.LECTERN_UPDATE_PACKET

        override fun serialize(value: LecternUpdatePacket, stream: Sink) {
            Proto.Byte.serialize(value.page, stream)
            Proto.Byte.serialize(value.totalPages, stream)
            UIVector3.serialize(value.blockPosition, stream)
        }

        override fun deserialize(stream: Source): LecternUpdatePacket {
            return LecternUpdatePacket(
                page = Proto.Byte.deserialize(stream),
                totalPages = Proto.Byte.deserialize(stream),
                blockPosition = UIVector3.deserialize(stream)
            )
        }
    }
}
