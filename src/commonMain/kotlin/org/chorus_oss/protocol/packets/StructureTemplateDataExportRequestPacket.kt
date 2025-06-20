package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos
import org.chorus_oss.protocol.types.structure.StructureSettings

data class StructureTemplateDataExportRequestPacket(
    val structureName: String,
    val position: BlockPos,
    val settings: StructureSettings,
    val requestType: RequestType,
) : Packet(id) {
    companion object : PacketCodec<StructureTemplateDataExportRequestPacket> {
        enum class RequestType(val net: Byte) {
            ExportFromSave(1),
            ExportFromLoad(2),
            QuerySavedStructure(3);

            companion object : ProtoCodec<RequestType> {
                override fun serialize(
                    value: RequestType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): RequestType {
                    return Proto.Byte.deserialize(stream).let {
                        entries.find { e -> e.net == it }!!
                    }
                }
            }
        }

        override val id: Int = 132

        override fun serialize(
            value: StructureTemplateDataExportRequestPacket,
            stream: Sink
        ) {
            Proto.String.serialize(value.structureName, stream)
            NetBlockPos.serialize(value.position, stream)
            StructureSettings.serialize(value.settings, stream)
            RequestType.serialize(value.requestType, stream)
        }

        override fun deserialize(stream: Source): StructureTemplateDataExportRequestPacket {
            return StructureTemplateDataExportRequestPacket(
                structureName = Proto.String.deserialize(stream),
                position = NetBlockPos.deserialize(stream),
                settings = StructureSettings.deserialize(stream),
                requestType = RequestType.deserialize(stream),
            )
        }
    }
}
