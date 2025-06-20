package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String


data class SubClientLoginPacket(
    val connectionRequest: String,
) : Packet(id) {
    companion object : PacketCodec<SubClientLoginPacket> {
        override val id: Int = 94

        override fun serialize(value: SubClientLoginPacket, stream: Sink) {
            Proto.String.serialize(value.connectionRequest, stream)
        }

        override fun deserialize(stream: Source): SubClientLoginPacket {
            return SubClientLoginPacket(
                connectionRequest = Proto.String.deserialize(stream),
            )
        }
    }
}
