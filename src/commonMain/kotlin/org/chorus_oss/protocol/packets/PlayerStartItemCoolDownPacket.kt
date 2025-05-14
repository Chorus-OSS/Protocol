package org.chorus_oss.protocol.packets


class PlayerStartItemCoolDownPacket : Packet(id) {
    @JvmField
    var itemCategory: String? = null

    @JvmField
    var coolDownDuration: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(itemCategory!!)
        byteBuf.writeVarInt(coolDownDuration)
    }

    override fun pid(): Int {
        return ProtocolInfo.PLAYER_START_ITEM_COOL_DOWN_PACKET
    }



    companion object : PacketCodec<PlayerStartItemCoolDownPacket> {
        override fun deserialize(stream: Source): PlayerStartItemCoolDownPacket {
            val packet = PlayerStartItemCoolDownPacket()

            packet.itemCategory = Proto.String.deserialize(stream)
            packet.coolDownDuration = byteBuf.readVarInt()

            return packet
        }
    }
}
