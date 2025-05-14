package org.chorus_oss.protocol.packets


class SetLastHurtByPacket : Packet(id) {
    override fun deserialize(stream: Source) {
    }

    override fun encode(byteBuf: ByteBuf) {
        //TODO: Implement
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_LAST_HURT_BY_PACKET
    }


}
