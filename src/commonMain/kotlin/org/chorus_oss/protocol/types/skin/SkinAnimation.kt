package org.chorus_oss.protocol.types.skin

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.UInt

data class SkinAnimation(
    val imageWidth: UInt,
    val imageHeight: UInt,
    val imageData: List<Byte>,
    val animationType: AnimationType,
    val frameCount: Float,
    val expressionType: ExpressionType,
) {
    companion object : ProtoCodec<SkinAnimation> {
        enum class AnimationType {
            Head,
            Body32x32,
            Body128x128;

            companion object : ProtoCodec<AnimationType> {
                override fun serialize(
                    value: AnimationType,
                    stream: Sink
                ) {
                    ProtoLE.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): AnimationType {
                    return entries[ProtoLE.UInt.deserialize(stream).toInt()]
                }
            }
        }

        enum class ExpressionType {
            Linear,
            Blinking;

            companion object : ProtoCodec<ExpressionType> {
                override fun serialize(
                    value: ExpressionType,
                    stream: Sink
                ) {
                    ProtoLE.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): ExpressionType {
                    return entries[ProtoLE.UInt.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(value: SkinAnimation, stream: Sink) {
            ProtoLE.UInt.serialize(value.imageWidth, stream)
            ProtoLE.UInt.serialize(value.imageHeight, stream)
            ProtoHelper.serializeList(value.imageData, stream, Proto.Byte)
            AnimationType.serialize(value.animationType, stream)
            ProtoLE.Float.serialize(value.frameCount, stream)
            ExpressionType.serialize(value.expressionType, stream)
        }

        override fun deserialize(stream: Source): SkinAnimation {
            return SkinAnimation(
                imageWidth = ProtoLE.UInt.deserialize(stream),
                imageHeight = ProtoLE.UInt.deserialize(stream),
                imageData = ProtoHelper.deserializeList(stream, Proto.Byte),
                animationType = AnimationType.deserialize(stream),
                frameCount = ProtoLE.Float.deserialize(stream),
                expressionType = ExpressionType.deserialize(stream),
            )
        }
    }
}
