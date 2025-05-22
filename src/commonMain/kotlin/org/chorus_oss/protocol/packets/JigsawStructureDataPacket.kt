package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte

data class JigsawStructureDataPacket(
    val data: List<Byte>
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
            ProtoHelper.serializeList(value.data, stream, Proto.Byte)
        }

        override fun deserialize(stream: Source): JigsawStructureDataPacket {
            return JigsawStructureDataPacket(
                data = ProtoHelper.deserializeList(stream, Proto.Byte)
            )
        }
    }
}
