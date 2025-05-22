package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.String


data class PlayerFogPacket(
    val stack: List<String>
) : Packet(id) {
    companion object : PacketCodec<PlayerFogPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.PLAYER_FOG_PACKET

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
