package org.chorus_oss.protocol.packets


import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.ActorRuntimeID

data class AnimatePacket(
    val action: Action,
    val targetRuntimeID: ActorRuntimeID,
    val actionData: Action.ActionData? = null,
) {
    enum class Action(val id: Int) {
        NO_ACTION(0),
        SWING_ARM(1),
        WAKE_UP(3),
        CRITICAL_HIT(4),
        MAGIC_CRITICAL_HIT(5),
        ROW_RIGHT(128),
        ROW_LEFT(129);

        companion object : ProtoCodec<Action> {
            override fun serialize(value: Action, stream: Buffer) {
                ProtoVAR.Int.serialize(value.id, stream)
            }

            override fun deserialize(stream: Buffer): Action {
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
        override val id: Int
            get() = ProtocolInfo.ANIMATE_PACKET

        override fun deserialize(stream: Buffer): AnimatePacket {
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

        override fun serialize(value: AnimatePacket, stream: Buffer) {
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
