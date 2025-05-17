package org.chorus_oss.protocol.types.camera.preset

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.Vector2f
import org.chorus_oss.protocol.types.camera.CameraAimAssistTargetMode

data class CameraPresetAimAssist(
    var presetId: String? = null,
    var targetMode: CameraAimAssistTargetMode? = null,
    val angle: Vector2f? = null,
    val distance: Float? = null,
) {
    companion object : ProtoCodec<CameraPresetAimAssist> {
        override fun serialize(value: CameraPresetAimAssist, stream: Sink) {
            ProtoHelper.serializeNullable(value.presetId, stream, Proto.String)
            ProtoHelper.serializeNullable(value.targetMode, stream) { m, buf ->
                ProtoLE.Int.serialize(m.ordinal, buf)
            }
            ProtoHelper.serializeNullable(value.angle, stream, Vector2f)
            ProtoHelper.serializeNullable(value.distance, stream, ProtoLE.Float)
        }

        override fun deserialize(stream: Source): CameraPresetAimAssist {
            return CameraPresetAimAssist(
                presetId = ProtoHelper.deserializeNullable(stream, Proto.String),
                targetMode = ProtoHelper.deserializeNullable(stream) { buf ->
                    CameraAimAssistTargetMode.entries[ProtoLE.Int.deserialize(buf)]
                },
                angle = ProtoHelper.deserializeNullable(stream, Vector2f),
                distance = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
            )
        }
    }
}
