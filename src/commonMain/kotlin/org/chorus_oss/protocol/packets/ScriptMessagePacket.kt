package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String


data class ScriptMessagePacket(
    val channel: String,
    val message: String,
) : Packet(id) {
    companion object : PacketCodec<ScriptMessagePacket> {
        override val id: Int = 177

        override fun serialize(value: ScriptMessagePacket, stream: Sink) {
            Proto.String.serialize(value.channel, stream)
            Proto.String.serialize(value.message, stream)
        }

        override fun deserialize(stream: Source): ScriptMessagePacket {
            return ScriptMessagePacket(
                channel = Proto.String.deserialize(stream),
                message = Proto.String.deserialize(stream),
            )
        }
    }
}
