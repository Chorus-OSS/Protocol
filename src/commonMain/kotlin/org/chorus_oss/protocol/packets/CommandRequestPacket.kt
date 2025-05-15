package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.CommandOriginData

data class CommandRequestPacket(
    val command: String,
    val commandOrigin: CommandOriginData,
    val isInternalSource: Boolean,
    val version: Int,
) : Packet(id) {
    companion object : PacketCodec<CommandRequestPacket> {
        override val id: Int
            get() = ProtocolInfo.COMMAND_REQUEST_PACKET

        override fun deserialize(stream: Source): CommandRequestPacket {
            return CommandRequestPacket(
                command = Proto.String.deserialize(stream),
                commandOrigin = CommandOriginData.deserialize(stream),
                isInternalSource = Proto.Boolean.deserialize(stream),
                version = ProtoVAR.Int.deserialize(stream),
            )
        }

        override fun serialize(value: CommandRequestPacket, stream: Sink) {
            Proto.String.serialize(value.command, stream)
            CommandOriginData.serialize(value.commandOrigin, stream)
            Proto.Boolean.serialize(value.isInternalSource, stream)
            ProtoVAR.Int.serialize(value.version, stream)
        }
    }
}
