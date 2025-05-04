package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class TickingAreasLoadStatusPacket : DataPacket() {
    var waitingForPreload: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(this.waitingForPreload)
    }

    override fun pid(): Int {
        return ProtocolInfo.TICKING_AREAS_LOAD_STATUS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
