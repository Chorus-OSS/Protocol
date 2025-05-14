package org.chorus_oss.protocol.packets


class SimpleEventPacket : Packet(id) {
    var type: Short = 0

    override fun deserialize(stream: Source) {
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeShort(type.toInt())
    }

    override fun pid(): Int {
        return ProtocolInfo.SIMPLE_EVENT_PACKET
    }


}
