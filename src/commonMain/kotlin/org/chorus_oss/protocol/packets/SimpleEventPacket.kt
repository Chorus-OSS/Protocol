package org.chorus_oss.protocol.packets


class SimpleEventPacket : Packet(id) {
    var type: Short = 0

    override fun decode(byteBuf: ByteBuf) {
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeShort(type.toInt())
    }

    override fun pid(): Int {
        return ProtocolInfo.SIMPLE_EVENT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
