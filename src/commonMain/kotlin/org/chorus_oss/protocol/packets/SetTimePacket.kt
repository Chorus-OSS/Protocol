package org.chorus_oss.protocol.packets


class SetTimePacket : Packet(id) {
    @JvmField
    var time: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.time)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_TIME_PACKET
    }


}
