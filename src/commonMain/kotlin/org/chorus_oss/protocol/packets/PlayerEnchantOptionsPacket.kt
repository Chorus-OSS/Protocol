package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.EnchantmentOption


data class PlayerEnchantOptionsPacket(
    val options: List<EnchantmentOption>
) : Packet(id) {
    companion object : PacketCodec<PlayerEnchantOptionsPacket> {
        override val id: Int = 146

        override fun serialize(
            value: PlayerEnchantOptionsPacket,
            stream: Sink
        ) {
            ProtoHelper.serializeList(value.options, stream, EnchantmentOption)
        }

        override fun deserialize(stream: Source): PlayerEnchantOptionsPacket {
            return PlayerEnchantOptionsPacket(
                options = ProtoHelper.deserializeList(stream, EnchantmentOption),
            )
        }
    }
}
