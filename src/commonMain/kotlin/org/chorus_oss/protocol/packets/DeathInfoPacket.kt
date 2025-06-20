package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.String


data class DeathInfoPacket(
    val cause: String,
    val messages: List<String>,
) : Packet(id) {
    companion object : PacketCodec<DeathInfoPacket> {
        override val id: Int = 189

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
