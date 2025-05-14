package org.chorus_oss.protocol.packets


class ToastRequestPacket : Packet(id) {
    @JvmField
    var title: String = ""

    @JvmField
    var content: String = ""

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(this.title)
        byteBuf.writeString(this.content)
    }

    override fun pid(): Int {
        return ProtocolInfo.TOAST_REQUEST_PACKET
    }



    companion object : PacketCodec<ToastRequestPacket> {
        override fun deserialize(stream: Source): ToastRequestPacket {
            val packet = ToastRequestPacket()

            packet.title = Proto.String.deserialize(stream)
            packet.content = Proto.String.deserialize(stream)

            return packet
        }
    }
}
