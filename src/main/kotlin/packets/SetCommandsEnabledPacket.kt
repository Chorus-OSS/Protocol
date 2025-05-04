package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class SetCommandsEnabledPacket : DataPacket() {
    var enabled: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(this.enabled)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_COMMANDS_ENABLED_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
