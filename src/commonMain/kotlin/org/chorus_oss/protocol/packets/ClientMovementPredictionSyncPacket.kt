package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.BitSet
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.types.Vector3f
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.MovementAttributesComponent
import org.chorus_oss.protocol.types.actor_data.ActorDataFlag

data class ClientMovementPredictionSyncPacket(
    val flags: Set<ActorDataFlag>,
    val actorBoundingBox: Vector3f,
    val movementAttributesComponent: MovementAttributesComponent,
    val actorUniqueID: ActorUniqueID,
    val actorFlyingState: Boolean,
) : Packet(id) {
    companion object : PacketCodec<ClientMovementPredictionSyncPacket> {
        override val id: Int
            get() = ProtocolInfo.CLIENT_MOVEMENT_PREDICTION_SYNC_PACKET

        override fun deserialize(stream: Source): ClientMovementPredictionSyncPacket {
            return ClientMovementPredictionSyncPacket(
                flags = run {
                    val bitSet = BitSet.deserialize(stream)
                    ActorDataFlag.entries.filter {
                        bitSet.getOrElse(it.ordinal) { false }
                    }.toMutableSet()
                },
                actorBoundingBox = Vector3f.deserialize(stream),
                movementAttributesComponent = MovementAttributesComponent.deserialize(stream),
                actorUniqueID = ActorUniqueID.deserialize(stream),
                actorFlyingState = Proto.Boolean.deserialize(stream),
            )
        }

        override fun serialize(value: ClientMovementPredictionSyncPacket, stream: Sink) {
            run {
                val bitSet = BitSet(
                    MutableList(value.flags.maxOf { it.ordinal } + 1) { false }.apply {
                        value.flags.forEach {
                            this[it.ordinal] = true
                        }
                    }
                )
                BitSet.serialize(bitSet, stream)
            }
            Vector3f.serialize(value.actorBoundingBox, stream)
            MovementAttributesComponent.serialize(value.movementAttributesComponent, stream)
            ActorUniqueID.serialize(value.actorUniqueID, stream)
            Proto.Boolean.serialize(value.actorFlyingState, stream)
        }
    }
}
