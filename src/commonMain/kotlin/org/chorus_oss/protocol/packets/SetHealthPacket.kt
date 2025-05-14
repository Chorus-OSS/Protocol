package org.chorus_oss.protocol.packets


class SetHealthPacket : Packet(id) {
    var health: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.health)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_HEALTH_PACKET
    }


}
