package org.chorus_oss.protocol.packets


class InitiateWebSocketConnectionPacket : Packet(id) {
    override fun deserialize(stream: Source) {
    }

    override fun encode(byteBuf: ByteBuf) {
        //TODO: Implement
    }

    override fun pid(): Int {
        return ProtocolInfo.INITIATE_WEB_SOCKET_CONNECTION_PACKET
    }


}
