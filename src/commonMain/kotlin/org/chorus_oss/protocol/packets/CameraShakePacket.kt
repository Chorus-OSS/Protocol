package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float


data class CameraShakePacket(
    val intensity: Float,
    val duration: Float,
    val type: Type,
    val action: Action,
) : Packet(id) {
    companion object : PacketCodec<CameraShakePacket> {
        enum class Action {
            Add,
            Stop;

            companion object : ProtoCodec<Action> {
                override fun serialize(value: Action, stream: Sink) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Action {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        enum class Type {
            Positional,
            Rotational;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Sink) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int = 159

        override fun deserialize(stream: Source): CameraShakePacket {
            return CameraShakePacket(
                intensity = ProtoLE.Float.deserialize(stream),
                duration = ProtoLE.Float.deserialize(stream),
                type = Type.deserialize(stream),
                action = Action.deserialize(stream)
            )
        }

        override fun serialize(value: CameraShakePacket, stream: Sink) {
            ProtoLE.Float.serialize(value.intensity, stream)
            ProtoLE.Float.serialize(value.duration, stream)
            Type.serialize(value.type, stream)
            Action.serialize(value.action, stream)
        }
    }
}
