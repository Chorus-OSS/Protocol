package org.chorus_oss.protocol.packets


class SettingsCommandPacket : Packet(id) {
    lateinit var command: String
    var suppressOutput: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(command)
        byteBuf.writeBoolean(suppressOutput)
    }

    override fun pid(): Int {
        return ProtocolInfo.SETTINGS_COMMAND_PACKET
    }



    companion object : PacketCodec<SettingsCommandPacket> {
        override fun deserialize(stream: Source): SettingsCommandPacket {
            val packet = SettingsCommandPacket()

            packet.command = Proto.String.deserialize(stream)
            packet.suppressOutput = Proto.Boolean.deserialize(stream)

            return packet
        }
    }
}
