package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String


data class PlayerStartItemCoolDownPacket(
    val category: String,
    val duration: Int
) : Packet(id) {
    companion object : PacketCodec<PlayerStartItemCoolDownPacket> {
        override val id: Int = 176

        override fun serialize(
            value: PlayerStartItemCoolDownPacket,
            stream: Sink
        ) {
            Proto.String.serialize(value.category, stream)
            ProtoVAR.Int.serialize(value.duration, stream)
        }

        override fun deserialize(stream: Source): PlayerStartItemCoolDownPacket {
            return PlayerStartItemCoolDownPacket(
                category = Proto.String.deserialize(stream),
                duration = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
