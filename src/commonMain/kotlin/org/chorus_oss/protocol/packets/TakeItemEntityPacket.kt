package org.chorus_oss.protocol.packets


class TakeItemEntityPacket : Packet(id) {
    @JvmField
    var entityId: Long = 0

    @JvmField
    var target: Long = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(this.target)
        byteBuf.writeActorRuntimeID(this.entityId)
    }

    override fun pid(): Int {
        return ProtocolInfo.TAKE_ITEM_ENTITY_PACKET
    }



    companion object : PacketCodec<TakeItemEntityPacket> {
        override fun deserialize(stream: Source): TakeItemEntityPacket {
            val packet = TakeItemEntityPacket()

            packet.target = byteBuf.readActorRuntimeID()
            packet.entityId = byteBuf.readActorRuntimeID()

            return packet
        }
    }
}
