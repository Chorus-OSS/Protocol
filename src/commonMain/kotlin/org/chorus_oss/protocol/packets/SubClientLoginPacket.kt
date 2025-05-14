package org.chorus_oss.protocol.packets


class SubClientLoginPacket : Packet(id) {
    override fun deserialize(stream: Source) {
    }

    override fun encode(byteBuf: ByteBuf) {
        //TODO: Implement
    }

    override fun pid(): Int {
        return ProtocolInfo.SUB_CLIENT_LOGIN_PACKET
    }


}
