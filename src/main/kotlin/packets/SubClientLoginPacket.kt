package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class SubClientLoginPacket : DataPacket() {
    override fun decode(byteBuf: ByteBuf) {
    }

    override fun encode(byteBuf: ByteBuf) {
        //TODO: Implement
    }

    override fun pid(): Int {
        return ProtocolInfo.SUB_CLIENT_LOGIN_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
