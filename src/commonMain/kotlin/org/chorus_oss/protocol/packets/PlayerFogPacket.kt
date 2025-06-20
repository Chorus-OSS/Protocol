package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.String


data class PlayerFogPacket(
    val stack: List<String>
) : Packet(id) {
    companion object : PacketCodec<PlayerFogPacket> {
        override val id: Int = 160

        override fun serialize(value: PlayerFogPacket, stream: Sink) {
            ProtoHelper.serializeList(value.stack, stream, Proto.String)
        }

        override fun deserialize(stream: Source): PlayerFogPacket {
            return PlayerFogPacket(
                stack = ProtoHelper.deserializeList(stream, Proto.String),
            )
        }
    }
}
