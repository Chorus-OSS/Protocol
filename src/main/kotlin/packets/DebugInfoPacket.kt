package org.chorus_oss.protocol.packets


class DebugInfoPacket : DataPacket() {
    var entityId: Long = 0
    var data: String? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeLong(this.entityId)
        byteBuf.writeString(data!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.DEBUG_INFO_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<DebugInfoPacket> {
        override fun decode(byteBuf: ByteBuf): DebugInfoPacket {
            val packet = DebugInfoPacket()

            packet.entityId = byteBuf.readLong()
            packet.data = byteBuf.readString()

            return packet
        }
    }
}
