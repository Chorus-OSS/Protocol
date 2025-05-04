package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

class ClientToServerHandshakePacket : DataPacket() {
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
