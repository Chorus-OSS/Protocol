package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.ULong

data class DebugShape(
    val id: ULong,
    val type: Type?,
    val position: Vector3f?,
    val scale: Float?,
    val rotation: Vector3f?,
    val remainingDuration: Float?,
    val text: String?,
    val boxBounds: Vector3f?,
    val lineEndPosition: Vector3f?,
    val arrowHeadLength: Float?,
    val arrowHeadRadius: Float?,
    val segments: Byte?,
) {
    companion object : ProtoCodec<DebugShape> {
        enum class Type {
            Line,
            Box,
            Sphere,
            Circle,
            Text,
            Arrow;

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(value: DebugShape, stream: Sink) {
            ProtoVAR.ULong.serialize(value.id, stream)
            ProtoHelper.serializeNullable(value.type, stream, Type)
            ProtoHelper.serializeNullable(value.position, stream, Vector3f)
            ProtoHelper.serializeNullable(value.scale, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.rotation, stream, Vector3f)
            ProtoHelper.serializeNullable(value.remainingDuration, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.text, stream, Proto.String)
            ProtoHelper.serializeNullable(value.boxBounds, stream, Vector3f)
            ProtoHelper.serializeNullable(value.lineEndPosition, stream, Vector3f)
            ProtoHelper.serializeNullable(value.arrowHeadLength, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.arrowHeadRadius, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.segments, stream, Proto.Byte)
        }

        override fun deserialize(stream: Source): DebugShape {
            return DebugShape(
                id = ProtoVAR.ULong.deserialize(stream),
                type = ProtoHelper.deserializeNullable(stream, Type),
                position = ProtoHelper.deserializeNullable(stream, Vector3f),
                scale = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                rotation = ProtoHelper.deserializeNullable(stream, Vector3f),
                remainingDuration = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                text = ProtoHelper.deserializeNullable(stream, Proto.String),
                boxBounds = ProtoHelper.deserializeNullable(stream, Vector3f),
                lineEndPosition = ProtoHelper.deserializeNullable(stream, Vector3f),
                arrowHeadLength = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                arrowHeadRadius = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                segments = ProtoHelper.deserializeNullable(stream, Proto.Byte),
            )
        }
    }
}
