package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString


data class AvailableActorIdentifiersPacket(
    val tag: ByteString,
) : Packet(id) {
    companion object : PacketCodec<AvailableActorIdentifiersPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.AVAILABLE_ACTOR_IDENTIFIERS_PACKET

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


