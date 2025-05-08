package org.chorus_oss.protocol.packets


class SettingsCommandPacket : DataPacket() {
    lateinit var command: String
    var suppressOutput: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(command)
        byteBuf.writeBoolean(suppressOutput)
    }

    override fun pid(): Int {
        return ProtocolInfo.SETTINGS_COMMAND_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<SettingsCommandPacket> {
        override fun decode(byteBuf: ByteBuf): SettingsCommandPacket {
            val packet = SettingsCommandPacket()

            packet.command = byteBuf.readString()
            packet.suppressOutput = byteBuf.readBoolean()

            return packet
        }
    }
}
