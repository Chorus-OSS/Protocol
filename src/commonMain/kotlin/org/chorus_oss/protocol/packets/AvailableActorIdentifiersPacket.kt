package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import kotlinx.io.readByteString
import kotlinx.io.write
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec


data class AvailableActorIdentifiersPacket(
    val tag: ByteString,
) : Packet(id) {
    companion object : PacketCodec<AvailableActorIdentifiersPacket> {
        override val id: Int = 119

        override fun deserialize(stream: Source): AvailableActorIdentifiersPacket {
            return AvailableActorIdentifiersPacket(
                tag = stream.readByteString(),
            )
        }

        override fun serialize(value: AvailableActorIdentifiersPacket, stream: Sink) {
            stream.write(value.tag)
        }
    }
}


