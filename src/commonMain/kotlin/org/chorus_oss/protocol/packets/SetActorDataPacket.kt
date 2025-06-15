package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.ActorProperties
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.actor_data.ActorDataMap

data class SetActorDataPacket(
    val actorRuntimeID: ActorRuntimeID,
    val actorDataMap: ActorDataMap,
    val actorProperties: ActorProperties,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<SetActorDataPacket> {
        override val id: Int = 39

        override fun serialize(value: SetActorDataPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.actorRuntimeID, stream)
            ActorDataMap.serialize(value.actorDataMap, stream)
            ActorProperties.serialize(value.actorProperties, stream)
            ProtoVAR.ULong.serialize(value.tick, stream)
        }

        override fun deserialize(stream: Source): SetActorDataPacket {
            return SetActorDataPacket(
                actorRuntimeID = ActorRuntimeID.deserialize(stream),
                actorDataMap = ActorDataMap.deserialize(stream),
                actorProperties = ActorProperties.deserialize(stream),
                tick = ProtoVAR.ULong.deserialize(stream),
            )
        }
    }
}
