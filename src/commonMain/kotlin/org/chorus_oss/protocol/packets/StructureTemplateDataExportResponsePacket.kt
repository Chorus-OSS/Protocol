package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.nbt.Tag
import org.chorus_oss.nbt.TagSerialization
import org.chorus_oss.nbt.tags.CompoundTag
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String

data class StructureTemplateDataExportResponsePacket(
    val structureName: String,
    val success: Boolean,
    val structureTemplate: CompoundTag?,
    val responseType: ResponseType,
) : Packet(id) {
    companion object : PacketCodec<StructureTemplateDataExportResponsePacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class ResponseType(val net: Byte) {
            Export(1),
            Query(2);

            companion object : ProtoCodec<ResponseType> {
                override fun serialize(
                    value: ResponseType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): ResponseType {
                    return Proto.Byte.deserialize(stream).let {
                        entries.find { e -> e.net == it }!!
                    }
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.STRUCTURE_TEMPLATE_DATA_EXPORT_RESPONSE_PACKET

        override fun serialize(
            value: StructureTemplateDataExportResponsePacket,
            stream: Sink
        ) {
            Proto.String.serialize(value.structureName, stream)
            Proto.Boolean.serialize(value.success, stream)
            when (value.success) {
                true -> Tag.serialize(value.structureTemplate as CompoundTag, stream, TagSerialization.NetLE, true)
                false -> Unit
            }
            ResponseType.serialize(value.responseType, stream)
        }

        override fun deserialize(stream: Source): StructureTemplateDataExportResponsePacket {
            val success: Boolean
            return StructureTemplateDataExportResponsePacket(
                structureName = Proto.String.deserialize(stream),
                success = Proto.Boolean.deserialize(stream).also { success = it },
                structureTemplate = when (success) {
                    true -> Tag.deserialize(stream, TagSerialization.NetLE) as CompoundTag
                    false -> null
                },
                responseType = ResponseType.deserialize(stream),
            )
        }
    }
}
