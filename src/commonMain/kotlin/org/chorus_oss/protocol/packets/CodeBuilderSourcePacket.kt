package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.CodeBuilderCategoryType
import org.chorus_oss.protocol.types.CodeBuilderCodeStatus
import org.chorus_oss.protocol.types.CodeBuilderOperationType

data class CodeBuilderSourcePacket(
    val operation: CodeBuilderOperationType,
    val category: CodeBuilderCategoryType,
    val codeStatus: CodeBuilderCodeStatus,
) : Packet(id) {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(operation.ordinal)
        byteBuf.writeByte(category.ordinal)
        byteBuf.writeByte(codeStatus.ordinal)
    }

    override fun pid(): Int {
        return ProtocolInfo.CODE_BUILDER_SOURCE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<CodeBuilderSourcePacket> {
        override fun decode(byteBuf: ByteBuf): CodeBuilderSourcePacket {
            return CodeBuilderSourcePacket(
                operation = CodeBuilderOperationType.entries[byteBuf.readByte().toInt()],
                category = CodeBuilderCategoryType.entries[byteBuf.readByte().toInt()],
                codeStatus = CodeBuilderCodeStatus.entries[byteBuf.readByte().toInt()],
            )
        }
    }
}
