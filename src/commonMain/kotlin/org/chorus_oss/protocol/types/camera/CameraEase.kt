package org.chorus_oss.protocol.types.camera

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float

data class CameraEase(
    val type: Type,
    val duration: Float,
) {
    companion object : ProtoCodec<CameraEase> {
        enum class Type {
            Linear,
            Spring,
            InQuad,
            OutQuad,
            InOutQuad,
            InCubic,
            OutCubic,
            InOutCubic,
            InQuart,
            OutQuart,
            InOutQuart,
            InQuint,
            OutQuint,
            InOutQuint,
            InSine,
            OutSine,
            InOutSine,
            InExpo,
            OutExpo,
            InOutExpo,
            InCirc,
            OutCirc,
            InOutCirc,
            InBounce,
            OutBounce,
            InOutBounce,
            InBack,
            OutBack,
            InOutBack,
            InElastic,
            OutElastic,
            InOutElastic;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Sink) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(value: CameraEase, stream: Sink) {
            Type.serialize(value.type, stream)
            ProtoLE.Float.serialize(value.duration, stream)
        }

        override fun deserialize(stream: Source): CameraEase {
            return CameraEase(
                type = Type.deserialize(stream),
                duration = ProtoLE.Float.deserialize(stream)
            )
        }
    }
}
