package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorUniqueID

data class ActorPickRequestPacket(
    val actorID: ActorUniqueID,
    val maxSlots: Byte,
    val withData: Boolean,
) : Packet(id) {
    companion object : PacketCodec<ActorPickRequestPacket> {
        override val id: Int = 35

        override fun deserialize(stream: Source): ActorPickRequestPacket {
            return ActorPickRequestPacket(
                actorID = ActorUniqueID.deserialize(stream),
                maxSlots = Proto.Byte.deserialize(stream),
                withData = Proto.Boolean.deserialize(stream),
            )
        }

        override fun serialize(value: ActorPickRequestPacket, stream: Sink) {
            ActorUniqueID.serialize(value.actorID, stream)
            Proto.Byte.serialize(value.maxSlots, stream)
            Proto.Boolean.serialize(value.withData, stream)
        }
    }
}
