package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorLink

data class SetActorLinkPacket(
    val actorLink: ActorLink,
) : Packet(id) {
    companion object : PacketCodec<SetActorLinkPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.SET_ACTOR_LINK_PACKET

        override fun serialize(value: SetActorLinkPacket, stream: Sink) {
            ActorLink.serialize(value.actorLink, stream)
        }

        override fun deserialize(stream: Source): SetActorLinkPacket {
            return SetActorLinkPacket(
                actorLink = ActorLink.deserialize(stream),
            )
        }
    }
}
