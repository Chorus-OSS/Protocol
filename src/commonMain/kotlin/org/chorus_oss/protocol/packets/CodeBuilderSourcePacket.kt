package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.CodeBuilderCategoryType
import org.chorus_oss.protocol.types.CodeBuilderCodeStatus
import org.chorus_oss.protocol.types.CodeBuilderOperationType

data class CodeBuilderSourcePacket(
    val operation: CodeBuilderOperationType,
    val category: CodeBuilderCategoryType,
    val codeStatus: CodeBuilderCodeStatus,
) : Packet(id) {
    companion object : PacketCodec<CodeBuilderSourcePacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.CODE_BUILDER_SOURCE_PACKET

        override fun deserialize(stream: Source): CodeBuilderSourcePacket {
            return CodeBuilderSourcePacket(
                operation = CodeBuilderOperationType.deserialize(stream),
                category = CodeBuilderCategoryType.deserialize(stream),
                codeStatus = CodeBuilderCodeStatus.deserialize(stream),
            )
        }

        override fun serialize(value: CodeBuilderSourcePacket, stream: Sink) {
            CodeBuilderOperationType.serialize(value.operation, stream)
            CodeBuilderCategoryType.serialize(value.category, stream)
            CodeBuilderCodeStatus.serialize(value.codeStatus, stream)
        }
    }
}
