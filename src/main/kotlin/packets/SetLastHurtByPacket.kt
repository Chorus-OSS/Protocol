package org.chorus_oss.protocol.packets


class SetLastHurtByPacket : DataPacket() {
    override fun decode(byteBuf: ByteBuf) {
    }

    override fun encode(byteBuf: ByteBuf) {
        //TODO: Implement
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_LAST_HURT_BY_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
