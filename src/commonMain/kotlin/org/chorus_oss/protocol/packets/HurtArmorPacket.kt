package org.chorus_oss.protocol.packets


class HurtArmorPacket(
    val cause: Int,
    val damage: Int,
    val armorSlots: Long,
) : Packet(id) {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.cause)
        byteBuf.writeVarInt(this.damage)
        byteBuf.writeUnsignedVarLong(this.armorSlots)
    }

    override fun pid(): Int {
        return ProtocolInfo.HURT_ARMOR_PACKET
    }



    companion object : PacketCodec<HurtArmorPacket> {
        override fun deserialize(stream: Source): HurtArmorPacket {
            return HurtArmorPacket(
                cause = byteBuf.readVarInt(),
                damage = byteBuf.readVarInt(),
                armorSlots = byteBuf.readUnsignedVarLong()
            )
        }
    }
}
