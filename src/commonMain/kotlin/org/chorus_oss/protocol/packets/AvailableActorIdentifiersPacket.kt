package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte


data class AvailableActorIdentifiersPacket(
    val tag: List<Byte>,
) {
    companion object : PacketCodec<AvailableActorIdentifiersPacket> {
        override val id: Int
            get() = ProtocolInfo.AVAILABLE_ACTOR_IDENTIFIERS_PACKET

        override fun deserialize(stream: Source): AvailableActorIdentifiersPacket {
            return AvailableActorIdentifiersPacket(
                tag = ProtoHelper.deserializeList(stream, Proto.Byte::deserialize),
            )
        }

        override fun serialize(value: AvailableActorIdentifiersPacket, stream: Sink) {
            ProtoHelper.serializeList(value.tag, stream, Proto.Byte::serialize)
        }
    }
}


