package org.chorus_oss.protocol.packets


class TickingAreasLoadStatusPacket : Packet(id) {
    var waitingForPreload: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(this.waitingForPreload)
    }

    override fun pid(): Int {
        return ProtocolInfo.TICKING_AREAS_LOAD_STATUS_PACKET
    }


}
