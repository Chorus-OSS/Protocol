package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

class HurtArmorPacket(
    val cause: Int,
    val damage: Int,
    val armorSlots: Long,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.cause)
        byteBuf.writeVarInt(this.damage)
        byteBuf.writeUnsignedVarLong(this.armorSlots)
    }

    override fun pid(): Int {
        return ProtocolInfo.HURT_ARMOR_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<HurtArmorPacket> {
        override fun decode(byteBuf: ByteBuf): HurtArmorPacket {
            return HurtArmorPacket(
                cause = byteBuf.readVarInt(),
                damage = byteBuf.readVarInt(),
                armorSlots = byteBuf.readUnsignedVarLong()
            )
        }
    }
}
