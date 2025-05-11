package org.chorus_oss.protocol.types.camera.instruction

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.types.Color
import org.chorus_oss.protocol.types.FColorRGB

data class CameraFadeInstruction(
    val timeData: TimeData? = null,
    val color: Color? = null,
) {
    companion object : ProtoCodec<CameraFadeInstruction> {
        data class TimeData(
            val fadeInDuration: Float,
            val waitDuration: Float,
            val fadeOutDuration: Float,
        ) {
            companion object : ProtoCodec<TimeData> {
                override fun serialize(value: TimeData, stream: Sink) {
                    ProtoLE.Float.serialize(value.fadeInDuration, stream)
                    ProtoLE.Float.serialize(value.waitDuration, stream)
                    ProtoLE.Float.serialize(value.fadeOutDuration, stream)
                }

                override fun deserialize(stream: Source): TimeData {
                    return TimeData(
                        fadeInDuration = ProtoLE.Float.deserialize(stream),
                        waitDuration = ProtoLE.Float.deserialize(stream),
                        fadeOutDuration = ProtoLE.Float.deserialize(stream),
                    )
                }
            }
        }

        override fun serialize(value: CameraFadeInstruction, stream: Sink) {
            ProtoHelper.serializeNullable(value.timeData, stream, TimeData::serialize)
            ProtoHelper.serializeNullable(value.color, stream, FColorRGB::serialize)
        }

        override fun deserialize(stream: Source): CameraFadeInstruction {
            return CameraFadeInstruction(
                timeData = ProtoHelper.deserializeNullable(stream, TimeData::deserialize),
                color = ProtoHelper.deserializeNullable(stream, FColorRGB::deserialize)
            )
        }
    }
}
