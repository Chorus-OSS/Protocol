package org.chorus_oss.protocol.packets


class MapCreateLockedCopyPacket : Packet(id) {
    var originalMapId: Long = 0
    var newMapId: Long = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarLong(this.originalMapId)
        byteBuf.writeVarLong(this.newMapId)
    }

    override fun pid(): Int {
        return ProtocolInfo.MAP_CREATE_LOCKED_COPY_PACKET
    }



    companion object : PacketCodec<MapCreateLockedCopyPacket> {
        override fun deserialize(stream: Source): MapCreateLockedCopyPacket {
            val packet = MapCreateLockedCopyPacket()

            packet.originalMapId = byteBuf.readVarLong()
            packet.newMapId = byteBuf.readVarLong()

            return packet
        }
    }
}
