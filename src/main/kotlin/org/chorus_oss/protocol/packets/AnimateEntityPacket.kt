package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Long
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
) {
    data class Animation(
        val animation: String,
        val nextState: String = DEFAULT_NEXT_STATE,
        val stopExpression: String = DEFAULT_STOP_EXPRESSION,
        val stopExpressionVersion: Int = DEFAULT_STOP_EXPRESSION_VERSION,
        val controller: String = DEFAULT_CONTROLLER,
        val blendOutTime: Float = DEFAULT_BLEND_OUT_TIME,
    ) {
        companion object {
            const val DEFAULT_BLEND_OUT_TIME: Float = 0.0f
            const val DEFAULT_STOP_EXPRESSION: String = "query.any_animation_finished"
            const val DEFAULT_CONTROLLER: String = "__runtime_controller"
            const val DEFAULT_NEXT_STATE: String = "default"
            const val DEFAULT_STOP_EXPRESSION_VERSION: Int = 16777216
        }
    }

    companion object : PacketCodec<AnimateEntityPacket> {
        override val id: Int
            get() = ProtocolInfo.ANIMATE_ENTITY_PACKET

        override fun deserialize(stream: Buffer): AnimateEntityPacket {
            return AnimateEntityPacket(
                animation = Proto.String.deserialize(stream),
                nextState = Proto.String.deserialize(stream),
                stopExpression = Proto.String.deserialize(stream),
                stopExpressionVersion = ProtoLE.Int.deserialize(stream),
                controller = Proto.String.deserialize(stream),
                blendOutTime = ProtoLE.Float.deserialize(stream),
                runtimeIDs = ProtoHelper.readList(stream, ProtoVAR.Long::deserialize)
            )
        }

        override fun serialize(value: AnimateEntityPacket, stream: Buffer) {
            Proto.String.serialize(value.animation, stream)
            Proto.String.serialize(value.nextState, stream)
            Proto.String.serialize(value.stopExpression, stream)
            ProtoLE.Int.serialize(value.stopExpressionVersion, stream)
            Proto.String.serialize(value.controller, stream)
            ProtoLE.Float.serialize(value.blendOutTime, stream)
            ProtoHelper.writeList(value.runtimeIDs, stream, ProtoVAR.Long::serialize)
        }

        fun fromAnimation(ani: Animation): AnimateEntityPacket {
            return AnimateEntityPacket(
                animation = ani.animation,
                nextState = ani.nextState,
                blendOutTime = ani.blendOutTime,
                stopExpression = ani.stopExpression,
                controller = ani.controller,
                stopExpressionVersion = ani.stopExpressionVersion,
                runtimeIDs = mutableListOf()
            )
        }
    }
}
