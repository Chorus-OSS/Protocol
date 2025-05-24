package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString

data class JigsawStructureDataPacket(
    val data: ByteString
) : Packet(id) {
    companion object : PacketCodec<JigsawStructureDataPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.JIGSAW_STRUCTURE_DATA_PACKET

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
