package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.shared.types.Vector3f

import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.ActorUniqueID

data class AddPaintingPacket(
    val targetActorID: ActorUniqueID,
    val targetRuntimeID: ActorRuntimeID,
    val position: Vector3f,
    val direction: Int,
    val motif: String,
) {
    companion object : PacketCodec<AddPaintingPacket> {
        override val id: Int
            get() = ProtocolInfo.ADD_PAINTING_PACKET

        override fun deserialize(stream: Buffer): AddPaintingPacket {
            return AddPaintingPacket(
                targetActorID = ProtoVAR.Long.deserialize(stream),
                targetRuntimeID = ProtoVAR.Long.deserialize(stream),
                position = Vector3f.deserialize(stream),
                direction = ProtoVAR.Int.deserialize(stream),
                motif = Proto.String.deserialize(stream),
            )
        }

        override fun serialize(value: AddPaintingPacket, stream: Buffer) {
            ProtoVAR.Long.serialize(value.targetActorID, stream)
            ProtoVAR.Long.serialize(value.targetRuntimeID, stream)
            Vector3f.serialize(value.position, stream)
            ProtoVAR.Int.serialize(value.direction, stream)
            Proto.String.serialize(value.motif, stream)
        }
    }
}
