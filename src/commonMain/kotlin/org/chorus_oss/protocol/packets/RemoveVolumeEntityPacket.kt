package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.ActorRuntimeID


data class RemoveVolumeEntityPacket(
    val entityRuntimeID: ActorRuntimeID,
    val dimension: Int,
) : Packet(id) {
    companion object : PacketCodec<RemoveVolumeEntityPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.REMOVE_VOLUME_ENTITY_PACKET

        override fun serialize(
            value: RemoveVolumeEntityPacket,
            stream: Sink
        ) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
        }

        override fun deserialize(stream: Source): RemoveVolumeEntityPacket {
            return RemoveVolumeEntityPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                dimension = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
