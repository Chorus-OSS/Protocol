package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.ULong


data class HurtArmorPacket(
    val cause: Int,
    val damage: Int,
    val armorSlots: ULong,
) : Packet(id) {
    companion object : PacketCodec<HurtArmorPacket> {
        override val id: Int
            get() = TODO("Not yet implemented")

        override fun serialize(value: HurtArmorPacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.cause, stream)
            ProtoVAR.Int.serialize(value.damage, stream)
            ProtoVAR.ULong.serialize(value.armorSlots, stream)
        }

        override fun deserialize(stream: Source): HurtArmorPacket {
            return HurtArmorPacket(
                cause = ProtoVAR.Int.deserialize(stream),
                damage = ProtoVAR.Int.deserialize(stream),
                armorSlots = ProtoVAR.ULong.deserialize(stream)
            )
        }
    }
}
