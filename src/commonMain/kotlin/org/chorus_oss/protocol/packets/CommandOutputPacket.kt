package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.CommandOriginData
import org.chorus_oss.protocol.types.CommandOutputMessage
import org.chorus_oss.protocol.types.CommandOutputType

data class CommandOutputPacket(
    val originData: CommandOriginData,
    val outputType: CommandOutputType,
    val successCount: UInt,
    val outputMessages: List<CommandOutputMessage>,
    val dataSet: String? = null,
) : Packet(id) {
    companion object : PacketCodec<CommandOutputPacket> {
        override val id: Int = 79

        override fun deserialize(stream: Source): CommandOutputPacket {
            val outputType: CommandOutputType
            return CommandOutputPacket(
                originData = CommandOriginData.deserialize(stream),
                outputType = CommandOutputType.deserialize(stream).also { outputType = it },
                successCount = ProtoVAR.UInt.deserialize(stream),
                outputMessages = ProtoHelper.deserializeList(stream, CommandOutputMessage),
                dataSet = when (outputType) {
                    CommandOutputType.DataSet -> Proto.String.deserialize(stream)

                    else -> null
                }
            )
        }

        override fun serialize(value: CommandOutputPacket, stream: Sink) {
            CommandOriginData.serialize(value.originData, stream)
            CommandOutputType.serialize(value.outputType, stream)
            ProtoVAR.UInt.serialize(value.successCount, stream)
            ProtoHelper.serializeList(value.outputMessages, stream, CommandOutputMessage)
            when (value.outputType) {
                CommandOutputType.DataSet -> (value.dataSet as String).let { Proto.String.serialize(it, stream) }

                else -> Unit
            }
        }
    }
}
