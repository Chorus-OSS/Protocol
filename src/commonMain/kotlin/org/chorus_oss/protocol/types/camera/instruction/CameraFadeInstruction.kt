package org.chorus_oss.protocol.types.camera.instruction

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float

data class CameraFadeInstruction(
    val timeData: TimeData? = null,
    val color: ColorData? = null,
) {
    companion object : ProtoCodec<CameraFadeInstruction> {
        data class TimeData(
            val fadeInDuration: Float,
            val waitDuration: Float,
            val fadeOutDuration: Float,
        ) {
            companion object : ProtoCodec<TimeData> {
                override fun serialize(value: TimeData, stream: Buffer) {
                    ProtoLE.Float.serialize(value.fadeInDuration, stream)
                    ProtoLE.Float.serialize(value.waitDuration, stream)
                    ProtoLE.Float.serialize(value.fadeOutDuration, stream)
                }

                override fun deserialize(stream: Buffer): TimeData {
                    return TimeData(
                        fadeInDuration = ProtoLE.Float.deserialize(stream),
                        waitDuration = ProtoLE.Float.deserialize(stream),
                        fadeOutDuration = ProtoLE.Float.deserialize(stream),
                    )
                }
            }
        }

        data class ColorData(
            val r: Float,
            val g: Float,
            val b: Float,
        ) {
            companion object : ProtoCodec<ColorData> {
                override fun serialize(value: ColorData, stream: Buffer) {
                    ProtoLE.Float.serialize(value.r, stream)
                    ProtoLE.Float.serialize(value.g, stream)
                    ProtoLE.Float.serialize(value.b, stream)
                }

                override fun deserialize(stream: Buffer): ColorData {
                    return ColorData(
                        r = ProtoLE.Float.deserialize(stream),
                        g = ProtoLE.Float.deserialize(stream),
                        b = ProtoLE.Float.deserialize(stream)
                    )
                }
            }
        }

        override fun serialize(value: CameraFadeInstruction, stream: Buffer) {
            ProtoHelper.serializeNullable(value.timeData, stream, TimeData::serialize)
            ProtoHelper.serializeNullable(value.color, stream, ColorData::serialize)
        }

        override fun deserialize(stream: Buffer): CameraFadeInstruction {
            return CameraFadeInstruction(
                timeData = ProtoHelper.deserializeNullable(stream, TimeData::deserialize),
                color = ProtoHelper.deserializeNullable(stream, ColorData::deserialize)
            )
        }
    }
}
