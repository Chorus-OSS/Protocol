package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.ByteString


data class AvailableActorIdentifiersPacket(
    val tag: ByteString,
) : Packet(id) {
    companion object : PacketCodec<AvailableActorIdentifiersPacket> {
        override val id: Int = 119

        override fun deserialize(stream: Source): AvailableActorIdentifiersPacket {
            return AvailableActorIdentifiersPacket(
                tag = Proto.ByteString.deserialize(stream),
            )
        }

        override fun serialize(value: AvailableActorIdentifiersPacket, stream: Sink) {
            Proto.ByteString.serialize(value.tag, stream)
        }
    }
}


