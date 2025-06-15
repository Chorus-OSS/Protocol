package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.ActorRuntimeID

data class AnimatePacket(
    val action: Action,
    val targetRuntimeID: ActorRuntimeID,
    val actionData: Action.ActionData? = null,
) : Packet(id) {
    enum class Action(val id: Int) {
        NO_ACTION(0),
        SWING_ARM(1),
        WAKE_UP(3),
        CRITICAL_HIT(4),
        MAGIC_CRITICAL_HIT(5),
        ROW_RIGHT(128),
        ROW_LEFT(129);

        companion object : ProtoCodec<Action> {
            override fun serialize(value: Action, stream: Sink) {
                ProtoVAR.Int.serialize(value.id, stream)
            }

            override fun deserialize(stream: Source): Action {
                val id = ProtoVAR.Int.deserialize(stream)
                return entries.find { it.id == id }!!
            }
        }

        interface ActionData
        data class RowingData(
            val rowingTime: Float
        ) : ActionData
    }

    companion object : PacketCodec<AnimatePacket> {
        override val id: Int = 44

        override fun deserialize(stream: Source): AnimatePacket {
            val action: Action
            return AnimatePacket(
                action = Action.deserialize(stream).also { action = it },
                targetRuntimeID = ActorRuntimeID.deserialize(stream),
                actionData = when (action) {
                    Action.ROW_LEFT,
                    Action.ROW_RIGHT -> Action.RowingData(
                        rowingTime = ProtoLE.Float.deserialize(stream)
                    )

                    else -> null
                }
            )
        }

        override fun serialize(value: AnimatePacket, stream: Sink) {
            Action.serialize(value.action, stream)
            ActorRuntimeID.serialize(value.targetRuntimeID, stream)
            when (value.action) {
                Action.ROW_LEFT,
                Action.ROW_RIGHT -> {
                    val actionData = value.actionData as Action.RowingData
                    ProtoLE.Float.serialize(actionData.rowingTime, stream)
                }

                else -> Unit
            }
        }
    }
}
