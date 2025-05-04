package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class InitiateWebSocketConnectionPacket : DataPacket() {
    override fun decode(byteBuf: ByteBuf) {
    }

    override fun encode(byteBuf: ByteBuf) {
        //TODO: Implement
    }

    override fun pid(): Int {
        return ProtocolInfo.INITIATE_WEB_SOCKET_CONNECTION_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
