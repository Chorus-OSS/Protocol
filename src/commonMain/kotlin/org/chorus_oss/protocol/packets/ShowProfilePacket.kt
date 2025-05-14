package org.chorus_oss.protocol.packets


class ShowProfilePacket : Packet(id) {
    @JvmField
    var xuid: String? = null

    override fun deserialize(stream: Source) {
        this.xuid = Proto.String.deserialize(stream)
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(xuid!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.SHOW_PROFILE_PACKET
    }


}
