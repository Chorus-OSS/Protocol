package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class ServerToClientHandshakePacket : DataPacket() {
    var jwt: String? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(jwt!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.SERVER_TO_CLIENT_HANDSHAKE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
