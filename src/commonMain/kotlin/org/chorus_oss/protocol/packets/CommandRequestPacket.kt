package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.CommandOriginData

data class CommandRequestPacket(
    val command: String,
    val commandOrigin: CommandOriginData,
    val isInternalSource: Boolean,
    val version: Int,
) : Packet(id) {
    override fun pid(): Int {
        return ProtocolInfo.COMMAND_REQUEST_PACKET
    }



    companion object : PacketCodec<CommandRequestPacket> {
        override fun deserialize(stream: Source): CommandRequestPacket {
            return CommandRequestPacket(
                command = Proto.String.deserialize(stream),
                commandOrigin = byteBuf.readCommandOriginData(),
                isInternalSource = Proto.Boolean.deserialize(stream),
                version = byteBuf.readVarInt()
            )
        }
    }
}
