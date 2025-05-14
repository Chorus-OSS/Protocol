package org.chorus_oss.protocol.packets


class RemoveVolumeEntityPacket : Packet(id) {
    var id: Long = 0

    var dimension: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(id.toInt())
    }

    override fun pid(): Int {
        return ProtocolInfo.REMOVE_VOLUME_ENTITY_PACKET
    }



    companion object : PacketCodec<RemoveVolumeEntityPacket> {
        override fun deserialize(stream: Source): RemoveVolumeEntityPacket {
            val packet = RemoveVolumeEntityPacket()

            packet.id = byteBuf.readUnsignedVarInt().toLong()

            return packet
        }
    }
}
