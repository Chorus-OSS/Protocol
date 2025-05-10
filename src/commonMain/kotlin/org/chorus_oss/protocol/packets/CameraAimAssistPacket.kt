package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.shared.types.Vector2f
import org.chorus_oss.protocol.types.camera.CameraAimAssistTargetMode

data class CameraAimAssistPacket(
    val presetId: String,
    val viewAngle: Vector2f,
    val distance: Float,
    val targetMode: CameraAimAssistTargetMode,
    val action: Action,
) {
    companion object : PacketCodec<CameraAimAssistPacket> {
        enum class Action {
            SET,
            CLEAR;

            companion object : ProtoCodec<Action> {
                override fun serialize(value: Action, stream: Buffer) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Buffer): Action {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.CAMERA_AIM_ASSIST_PACKET

        override fun deserialize(stream: Buffer): CameraAimAssistPacket {
            return CameraAimAssistPacket(
                presetId = Proto.String.deserialize(stream),
                viewAngle = Vector2f.deserialize(stream),
                distance = ProtoLE.Float.deserialize(stream),
                targetMode = CameraAimAssistTargetMode.deserialize(stream),
                action = Action.deserialize(stream)
            )
        }

        override fun serialize(value: CameraAimAssistPacket, stream: Buffer) {
            Proto.String.serialize(value.presetId, stream)
            Vector2f.serialize(value.viewAngle, stream)
            ProtoLE.Float.serialize(value.distance, stream)
            CameraAimAssistTargetMode.serialize(value.targetMode, stream)
            Action.serialize(value.action, stream)
        }
    }
}
