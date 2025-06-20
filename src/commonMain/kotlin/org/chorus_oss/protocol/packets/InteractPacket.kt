package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.Vector3f

data class InteractPacket(
    val action: Action,
    val targetRuntimeID: ULong,
    val actionData: ActionData?,
) : Packet(id) {
    companion object : PacketCodec<InteractPacket> {
        enum class Action(val netOrdinal: Byte) {
            Invalid(0),
            StopRiding(3),
            InteractUpdate(4),
            NpcOpen(5),
            OpenInventory(6);

            companion object : ProtoCodec<Action> {
                override fun serialize(
                    value: Action,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.netOrdinal, stream)
                }

                override fun deserialize(stream: Source): Action {
                    return Proto.Byte.deserialize(stream).let {
                        entries.find { e -> e.netOrdinal == it }!!
                    }
                }
            }
        }

        interface ActionData

        data class PositionData(
            val position: Vector3f
        ) : ActionData {
            companion object : ProtoCodec<PositionData> {
                override fun serialize(
                    value: PositionData,
                    stream: Sink
                ) {
                    Vector3f.serialize(value.position, stream)
                }

                override fun deserialize(stream: Source): PositionData {
                    return PositionData(
                        position = Vector3f.deserialize(stream)
                    )
                }
            }
        }

        override val id: Int = 33

        override fun serialize(value: InteractPacket, stream: Sink) {
            Action.serialize(value.action, stream)
            ActorRuntimeID.serialize(value.targetRuntimeID, stream)
            when (value.action) {
                Action.InteractUpdate,
                Action.StopRiding -> (value.actionData as PositionData).let { PositionData.serialize(it, stream) }

                else -> Unit
            }
        }

        override fun deserialize(stream: Source): InteractPacket {
            val action: Action
            return InteractPacket(
                action = Action.deserialize(stream).also { action = it },
                targetRuntimeID = ActorRuntimeID.deserialize(stream),
                actionData = when (action) {
                    Action.InteractUpdate,
                    Action.StopRiding -> PositionData.deserialize(stream)

                    else -> null
                }
            )
        }
    }
}
