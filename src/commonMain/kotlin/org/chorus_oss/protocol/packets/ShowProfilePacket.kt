package org.chorus_oss.protocol.packets


class ShowProfilePacket : Packet(id) {
    @JvmField
    var xuid: String? = null

    override fun decode(byteBuf: ByteBuf) {
        this.xuid = byteBuf.readString()
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(xuid!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.SHOW_PROFILE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
