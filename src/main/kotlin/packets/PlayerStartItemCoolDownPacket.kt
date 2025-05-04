package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class PlayerStartItemCoolDownPacket : DataPacket() {
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

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<PlayerStartItemCoolDownPacket> {
        override fun decode(byteBuf: ByteBuf): PlayerStartItemCoolDownPacket {
            val packet = PlayerStartItemCoolDownPacket()

            packet.itemCategory = byteBuf.readString()
            packet.coolDownDuration = byteBuf.readVarInt()

            return packet
        }
    }
}
