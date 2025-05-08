package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.types.ActorUniqueID

data class ActorPickRequestPacket(
    val actorID: ActorUniqueID,
    val maxSlots: Byte,
    val withData: Boolean,
) {
    companion object : PacketCodec<ActorPickRequestPacket> {
        override val id: Int
            get() = ProtocolInfo.ACTOR_PICK_REQUEST_PACKET

        override fun deserialize(stream: Buffer): ActorPickRequestPacket {
            return ActorPickRequestPacket(
                actorID = ProtoVAR.Long.deserialize(stream),
                maxSlots = Proto.Byte.deserialize(stream),
                withData = Proto.Boolean.deserialize(stream),
            )
        }

        override fun serialize(value: ActorPickRequestPacket, stream: Buffer) {
            ProtoVAR.Long.serialize(value.actorID, stream)
            Proto.Byte.serialize(value.maxSlots, stream)
            Proto.Boolean.serialize(value.withData, stream)
        }
    }
}
