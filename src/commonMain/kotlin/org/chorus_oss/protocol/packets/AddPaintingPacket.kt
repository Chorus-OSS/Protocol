package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.Vector3f

import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.ActorUniqueID

data class AddPaintingPacket(
    val actorUniqueID: ActorUniqueID,
    val actorRuntimeID: ActorRuntimeID,
    val position: Vector3f,
    val direction: Int,
    val motif: String,
) : Packet(id) {
    companion object : PacketCodec<AddPaintingPacket> {
        override val id: Int
            get() = ProtocolInfo.ADD_PAINTING_PACKET

        override fun deserialize(stream: Source): AddPaintingPacket {
            return AddPaintingPacket(
                actorUniqueID = ActorUniqueID.deserialize(stream),
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                position = Vector3f.deserialize(stream),
                direction = ProtoVAR.Int.deserialize(stream),
                motif = Proto.String.deserialize(stream),
            )
        }

        override fun serialize(value: AddPaintingPacket, stream: Sink) {
            ActorUniqueID.serialize(value.actorUniqueID, stream)
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            Vector3f.serialize(value.position, stream)
            ProtoVAR.Int.serialize(value.direction, stream)
            Proto.String.serialize(value.motif, stream)
        }
    }
}
