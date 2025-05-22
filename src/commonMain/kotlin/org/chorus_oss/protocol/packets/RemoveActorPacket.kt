package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorUniqueID


data class RemoveActorPacket(
    val actorUniqueID: ActorUniqueID
) : Packet(id) {
    companion object : PacketCodec<RemoveActorPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.REMOVE_ENTITY_PACKET

        override fun serialize(value: RemoveActorPacket, stream: Sink) {
            ActorUniqueID.serialize(value.actorUniqueID, stream)
        }

        override fun deserialize(stream: Source): RemoveActorPacket {
            return RemoveActorPacket(
                actorUniqueID = ActorUniqueID.deserialize(stream)
            )
        }
    }
}
