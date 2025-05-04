package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.ActorRuntimeID

data class AnimateEntityPacket(
    val animation: String,
    val nextState: String,
    val stopExpression: String,
    val stopExpressionVersion: Int,
    val controller: String,
    val blendOutTime: Float,
    val runtimeIDs: MutableList<ActorRuntimeID>,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(this.animation)
        byteBuf.writeString(this.nextState)
        byteBuf.writeString(this.stopExpression)
        byteBuf.writeInt(this.stopExpressionVersion)
        byteBuf.writeString(this.controller)
        byteBuf.writeFloatLE(this.blendOutTime)
        byteBuf.writeArray(this.runtimeIDs) { buf, id ->
            buf.writeActorRuntimeID(id)
        }
    }

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

    override fun pid(): Int {
        return ProtocolInfo.ANIMATE_ENTITY_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object {
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
