package org.chorus_oss.protocol.types.camera.preset

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.shared.types.Vector2f
import org.chorus_oss.protocol.types.camera.CameraAimAssistTargetMode

data class CameraPresetAimAssist(
    var presetId: String? = null,
    var targetMode: CameraAimAssistTargetMode? = null,
    val angle: Vector2f? = null,
    val distance: Float? = null,
) {
    companion object : ProtoCodec<CameraPresetAimAssist> {
        override fun serialize(value: CameraPresetAimAssist, stream: Buffer) {
            ProtoHelper.serializeNullable(value.presetId, stream, Proto.String::serialize)
            ProtoHelper.serializeNullable(value.targetMode, stream, CameraAimAssistTargetMode::serialize) // TODO: Byte or Int?
            ProtoHelper.serializeNullable(value.angle, stream, Vector2f::serialize)
            ProtoHelper.serializeNullable(value.distance, stream, ProtoLE.Float::serialize)
        }

        override fun deserialize(stream: Buffer): CameraPresetAimAssist {
            return CameraPresetAimAssist(
                presetId = ProtoHelper.deserializeNullable(stream, Proto.String::deserialize),
                targetMode = ProtoHelper.deserializeNullable(stream, CameraAimAssistTargetMode::deserialize),
                angle = ProtoHelper.deserializeNullable(stream, Vector2f::deserialize),
                distance = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
            )
        }
    }
}
