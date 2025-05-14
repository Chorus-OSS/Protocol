package org.chorus_oss.protocol.packets


class SetCommandsEnabledPacket : Packet(id) {
    var enabled: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(this.enabled)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_COMMANDS_ENABLED_PACKET
    }


}
