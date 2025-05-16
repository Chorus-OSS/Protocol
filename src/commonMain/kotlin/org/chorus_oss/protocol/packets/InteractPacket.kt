package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.types.ActorRuntimeID

class InteractPacket(
    val action: Action,
    val targetRuntimeID: ActorRuntimeID,
    val actionData: ActionData?,
) : Packet(id) {
    companion object : PacketCodec<InteractPacket> {
        enum class Action(val netOrdinal: Byte) {
            INVALID(0),
            STOP_RIDING(3),
            INTERACT_UPDATE(4),
            NPC_OPEN(5),
            OPEN_INVENTORY(6);

            companion object : ProtoCodec<Action> {
                override fun serialize(
                    value: Action,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Action {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        interface ActionData

        data class PositionData(
            val positionX: Float,
            val positionY: Float,
            val positionZ: Float,
        ) : ActionData {
            companion object : ProtoCodec<PositionData> {
                override fun serialize(
                    value: PositionData,
                    stream: Sink
                ) {
                    ProtoLE.Float.serialize(value.positionX, stream)
                    ProtoLE.Float.serialize(value.positionY, stream)
                    ProtoLE.Float.serialize(value.positionZ, stream)
                }

                override fun deserialize(stream: Source): PositionData {
                    return PositionData(
                        positionX = ProtoLE.Float.deserialize(stream),
                        positionY = ProtoLE.Float.deserialize(stream),
                        positionZ = ProtoLE.Float.deserialize(stream)
                    )
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.INTERACT_PACKET

        override fun serialize(value: InteractPacket, stream: Sink) {
            Action.serialize(value.action, stream)
            ActorRuntimeID.serialize(value.targetRuntimeID, stream)
            when (value.action) {
                Action.INTERACT_UPDATE,
                Action.STOP_RIDING -> (value.actionData as PositionData).let { PositionData.serialize(it, stream) }

                else -> Unit
            }
        }

        override fun deserialize(stream: Source): InteractPacket {
            val action: Action
            return InteractPacket(
                action = Action.deserialize(stream).also { action = it },
                targetRuntimeID = ActorRuntimeID.deserialize(stream),
                actionData = when (action) {
                    Action.INTERACT_UPDATE,
                    Action.STOP_RIDING -> PositionData.deserialize(stream)

                    else -> null
                }
            )
        }
    }
}
