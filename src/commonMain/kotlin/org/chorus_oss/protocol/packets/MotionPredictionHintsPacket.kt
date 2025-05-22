package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.Vector3f

data class MotionPredictionHintsPacket(
    val entityRuntimeID: ActorRuntimeID,
    val velocity: Vector3f,
    val onGround: Boolean
) : Packet(id) {
    companion object : PacketCodec<MotionPredictionHintsPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.MOTION_PREDICTION_HINTS_PACKET

        override fun serialize(
            value: MotionPredictionHintsPacket,
            stream: Sink
        ) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            Vector3f.serialize(value.velocity, stream)
            Proto.Boolean.serialize(value.onGround, stream)
        }

        override fun deserialize(stream: Source): MotionPredictionHintsPacket {
            return MotionPredictionHintsPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                velocity = Vector3f.deserialize(stream),
                onGround = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
