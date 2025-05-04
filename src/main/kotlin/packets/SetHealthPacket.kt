package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class SetHealthPacket : DataPacket() {
    var health: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.health)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_HEALTH_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
