package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3
import org.chorus_oss.protocol.types.structure.StructureSettings

data class StructureTemplateDataExportRequestPacket(
    val structureName: String,
    val position: IVector3,
    val settings: StructureSettings,
    val requestType: RequestType,
) : Packet(id) {
    companion object : PacketCodec<StructureTemplateDataExportRequestPacket> {
        init { PacketRegistry.register(this) }

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

        override val id: Int
            get() = ProtocolInfo.STRUCTURE_TEMPLATE_DATA_EXPORT_REQUEST_PACKET

        override fun serialize(
            value: StructureTemplateDataExportRequestPacket,
            stream: Sink
        ) {
            Proto.String.serialize(value.structureName, stream)
            UIVector3.serialize(value.position, stream)
            StructureSettings.serialize(value.settings, stream)
            RequestType.serialize(value.requestType, stream)
        }

        override fun deserialize(stream: Source): StructureTemplateDataExportRequestPacket {
            return StructureTemplateDataExportRequestPacket(
                structureName = Proto.String.deserialize(stream),
                position = UIVector3.deserialize(stream),
                settings = StructureSettings.deserialize(stream),
                requestType = RequestType.deserialize(stream),
            )
        }
    }
}
