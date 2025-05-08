package org.chorus_oss.protocol.packets


class TakeItemEntityPacket : DataPacket() {
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

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<TakeItemEntityPacket> {
        override fun decode(byteBuf: ByteBuf): TakeItemEntityPacket {
            val packet = TakeItemEntityPacket()

            packet.target = byteBuf.readActorRuntimeID()
            packet.entityId = byteBuf.readActorRuntimeID()

            return packet
        }
    }
}
