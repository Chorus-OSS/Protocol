package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

data class Vector3f(
    val x: Float,
    val y: Float,
    val z: Float
) {
    companion object : ProtoCodec<Vector3f> {
        override fun serialize(value: Vector3f, stream: Sink) {
            ProtoLE.Float.serialize(value.x, stream)
            ProtoLE.Float.serialize(value.y, stream)
            ProtoLE.Float.serialize(value.z, stream)
        }

        override fun deserialize(stream: Source): Vector3f {
            return Vector3f(
                x = ProtoLE.Float.deserialize(stream),
                y = ProtoLE.Float.deserialize(stream),
                z = ProtoLE.Float.deserialize(stream),
            )
        }
    }
}
