package org.chorus_oss.protocol.packets


import java.util.*


class PlayerArmorDamagePacket : Packet(id) {
    val flags: MutableSet<PlayerArmorDamageFlag> = EnumSet.noneOf(
        PlayerArmorDamageFlag::class.java
    )
    val damage: IntArray = IntArray(5)

    override fun encode(byteBuf: ByteBuf) {
        var outflags = 0
        for (flag in this.flags) {
            outflags = outflags or (1 shl flag.ordinal)
        }
        byteBuf.writeByte(outflags)

        for (flag in this.flags) {
            byteBuf.writeVarInt(damage[flag.ordinal])
        }
    }

    enum class PlayerArmorDamageFlag {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        BODY
    }

    override fun pid(): Int {
        return ProtocolInfo.PLAYER_ARMOR_DAMAGE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<PlayerArmorDamagePacket> {
        override fun decode(byteBuf: ByteBuf): PlayerArmorDamagePacket {
            val packet = PlayerArmorDamagePacket()

            val flags = byteBuf.readByte().toInt()
            for (i in 0..4) {
                if ((flags and (1 shl i)) != 0) {
                    packet.flags.add(PlayerArmorDamageFlag.entries[i])
                    packet.damage[i] = byteBuf.readVarInt()
                }
            }

            return packet
        }
    }
}
