package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class SetTimePacket : DataPacket() {
    @JvmField
    var time: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.time)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_TIME_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
