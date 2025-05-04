package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.CommandOriginData
import org.chorus_oss.protocol.types.CommandOutputMessage
import org.chorus_oss.protocol.types.CommandOutputType

data class CommandOutputPacket(
    val originData: CommandOriginData,
    val outputType: CommandOutputType,
    val successCount: Int,
    val outputMessages: List<CommandOutputMessage>,
    val dataSet: String? = null,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeCommandOriginData(this.originData)

        byteBuf.writeByte(this.outputType.ordinal)
        byteBuf.writeUnsignedVarInt(this.successCount)
        byteBuf.writeArray(outputMessages) { buf, msg ->
            buf.writeBoolean(msg.internal)
            buf.writeString(msg.messageId)
            buf.writeArray(msg.parameters.toList()) { buf1, param ->
                buf1.writeString(param)
            }
        }

        when (this.outputType) {
            CommandOutputType.DATA_SET -> {
                val dataSet = this.dataSet as String
                byteBuf.writeString(dataSet)
            }

            else -> Unit
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.COMMAND_OUTPUT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
