package org.chorus_oss.protocol.packets


class ClientToServerHandshakePacket : Packet(id) {
    override fun pid(): Int {
        return ProtocolInfo.CLIENT_TO_SERVER_HANDSHAKE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ClientToServerHandshakePacket> {
        override fun decode(byteBuf: ByteBuf): ClientToServerHandshakePacket {
            return ClientToServerHandshakePacket()
        }
    }
}
