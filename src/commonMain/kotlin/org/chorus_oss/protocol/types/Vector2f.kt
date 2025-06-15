package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float


data class Vector2f(
    val x: Float,
    val y: Float
) {
    companion object : ProtoCodec<Vector2f> {
        override fun serialize(value: Vector2f, stream: Sink) {
            ProtoLE.Float.serialize(value.x, stream)
            ProtoLE.Float.serialize(value.y, stream)
        }

        override fun deserialize(stream: Source): Vector2f {
            return Vector2f(
                x = ProtoLE.Float.deserialize(stream),
                y = ProtoLE.Float.deserialize(stream),
            )
        }
    }
}
