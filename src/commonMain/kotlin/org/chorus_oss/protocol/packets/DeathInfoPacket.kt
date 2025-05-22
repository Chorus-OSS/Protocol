package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.String


data class DeathInfoPacket(
    val cause: String,
    val messages: List<String>,
) : Packet(id) {
    companion object : PacketCodec<DeathInfoPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.DEATH_INFO_PACKET

        override fun serialize(value: DeathInfoPacket, stream: Sink) {
            Proto.String.serialize(value.cause, stream)
            ProtoHelper.serializeList(value.messages, stream, Proto.String)
        }

        override fun deserialize(stream: Source): DeathInfoPacket {
            return DeathInfoPacket(
                cause = Proto.String.deserialize(stream),
                messages = ProtoHelper.deserializeList(stream, Proto.String)
            )
        }
    }
}
