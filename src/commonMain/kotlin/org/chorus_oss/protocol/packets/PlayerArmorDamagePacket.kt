package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Short


data class PlayerArmorDamagePacket(
    val list: List<Entry>
) : Packet(id) {
    data class Entry(
        val slot: Int,
        val damage: Short,
    ) {
        companion object : ProtoCodec<Entry> {
            override fun serialize(
                value: Entry,
                stream: Sink
            ) {
                ProtoVAR.Int.serialize(value.slot, stream)
                ProtoLE.Short.serialize(value.damage, stream)
            }

            override fun deserialize(stream: Source): Entry {
                return Entry(
                    slot = ProtoVAR.Int.deserialize(stream),
                    damage = ProtoLE.Short.deserialize(stream),
                )
            }
        }
    }

    companion object : PacketCodec<PlayerArmorDamagePacket> {
        override val id: Int = 149

        override fun serialize(
            value: PlayerArmorDamagePacket,
            stream: Sink
        ) {
            ProtoHelper.serializeList(value.list, stream, Entry)
        }

        override fun deserialize(stream: Source): PlayerArmorDamagePacket {
            return PlayerArmorDamagePacket(
                list = ProtoHelper.deserializeList(stream, Entry)
            )
        }
    }
}
