package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.ByteString

data class JigsawStructureDataPacket(
    val data: ByteString
) : Packet(id) {
    companion object : PacketCodec<JigsawStructureDataPacket> {
        override val id: Int = 313

        override fun serialize(
            value: JigsawStructureDataPacket,
            stream: Sink
        ) {
            Proto.ByteString.serialize(value.data, stream)
        }

        override fun deserialize(stream: Source): JigsawStructureDataPacket {
            return JigsawStructureDataPacket(
                data = Proto.ByteString.deserialize(stream),
            )
        }
    }
}
