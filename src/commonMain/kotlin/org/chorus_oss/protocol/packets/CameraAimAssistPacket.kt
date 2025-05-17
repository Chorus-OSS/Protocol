package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.Vector2f
import org.chorus_oss.protocol.types.camera.CameraAimAssistTargetMode

data class CameraAimAssistPacket(
    val presetId: String,
    val viewAngle: Vector2f,
    val distance: Float,
    val targetMode: CameraAimAssistTargetMode,
    val action: Action,
) : Packet(id) {
    companion object : PacketCodec<CameraAimAssistPacket> {
        enum class Action {
            SET,
            CLEAR;

            companion object : ProtoCodec<Action> {
                override fun serialize(value: Action, stream: Sink) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Action {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.CAMERA_AIM_ASSIST_PACKET

        override fun deserialize(stream: Source): CameraAimAssistPacket {
            return CameraAimAssistPacket(
                presetId = Proto.String.deserialize(stream),
                viewAngle = Vector2f.deserialize(stream),
                distance = ProtoLE.Float.deserialize(stream),
                targetMode = CameraAimAssistTargetMode.deserialize(stream),
                action = Action.deserialize(stream)
            )
        }

        override fun serialize(value: CameraAimAssistPacket, stream: Sink) {
            Proto.String.serialize(value.presetId, stream)
            Vector2f.serialize(value.viewAngle, stream)
            ProtoLE.Float.serialize(value.distance, stream)
            CameraAimAssistTargetMode.serialize(value.targetMode, stream)
            Action.serialize(value.action, stream)
        }
    }
}
