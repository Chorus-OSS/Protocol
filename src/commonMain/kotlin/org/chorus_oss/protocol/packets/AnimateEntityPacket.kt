package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.ActorRuntimeID

data class AnimateEntityPacket(
    val animation: String,
    val nextState: String,
    val stopExpression: String,
    val stopExpressionVersion: Int,
    val controller: String,
    val blendOutTime: Float,
    val runtimeIDs: List<ActorRuntimeID>,
) : Packet(id) {
    companion object : PacketCodec<AnimateEntityPacket> {
        override val id: Int = 158

        override fun deserialize(stream: Source): AnimateEntityPacket {
            return AnimateEntityPacket(
                animation = Proto.String.deserialize(stream),
                nextState = Proto.String.deserialize(stream),
                stopExpression = Proto.String.deserialize(stream),
                stopExpressionVersion = ProtoLE.Int.deserialize(stream),
                controller = Proto.String.deserialize(stream),
                blendOutTime = ProtoLE.Float.deserialize(stream),
                runtimeIDs = ProtoHelper.deserializeList(stream, ActorRuntimeID)
            )
        }

        override fun serialize(value: AnimateEntityPacket, stream: Sink) {
            Proto.String.serialize(value.animation, stream)
            Proto.String.serialize(value.nextState, stream)
            Proto.String.serialize(value.stopExpression, stream)
            ProtoLE.Int.serialize(value.stopExpressionVersion, stream)
            Proto.String.serialize(value.controller, stream)
            ProtoLE.Float.serialize(value.blendOutTime, stream)
            ProtoHelper.serializeList(value.runtimeIDs, stream, ActorRuntimeID)
        }
    }
}
