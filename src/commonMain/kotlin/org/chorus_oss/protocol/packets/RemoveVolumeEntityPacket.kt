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

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<RemoveVolumeEntityPacket> {
        override fun decode(byteBuf: ByteBuf): RemoveVolumeEntityPacket {
            val packet = RemoveVolumeEntityPacket()

            packet.id = byteBuf.readUnsignedVarInt().toLong()

            return packet
        }
    }
}
