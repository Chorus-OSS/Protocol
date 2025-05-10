package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float


data class CameraShakePacket(
    val intensity: Float,
    val duration: Float,
    val type: Type,
    val action: Action,
) {
    companion object : PacketCodec<CameraShakePacket> {
        enum class Action {
            ADD,
            STOP;

            companion object : ProtoCodec<Action> {
                override fun serialize(value: Action, stream: Buffer) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Buffer): Action {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        enum class Type {
            POSITIONAL,
            ROTATIONAL;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Buffer) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Buffer): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.CAMERA_SHAKE_PACKET

        override fun deserialize(stream: Buffer): CameraShakePacket {
            return CameraShakePacket(
                intensity = ProtoLE.Float.deserialize(stream),
                duration = ProtoLE.Float.deserialize(stream),
                type = Type.deserialize(stream),
                action = Action.deserialize(stream)
            )
        }

        override fun serialize(value: CameraShakePacket, stream: Buffer) {
            ProtoLE.Float.serialize(value.intensity, stream)
            ProtoLE.Float.serialize(value.duration, stream)
            Type.serialize(value.type, stream)
            Action.serialize(value.action, stream)
        }
    }
}
