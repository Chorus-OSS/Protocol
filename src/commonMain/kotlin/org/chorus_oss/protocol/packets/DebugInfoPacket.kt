package org.chorus_oss.protocol.packets


class DebugInfoPacket : Packet(id) {
    var entityId: Long = 0
    var data: String? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeLong(this.entityId)
        byteBuf.writeString(data!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.DEBUG_INFO_PACKET
    }



    companion object : PacketCodec<DebugInfoPacket> {
        override fun deserialize(stream: Source): DebugInfoPacket {
            val packet = DebugInfoPacket()

            packet.entityId = byteBuf.readLong()
            packet.data = Proto.String.deserialize(stream)

            return packet
        }
    }
}
