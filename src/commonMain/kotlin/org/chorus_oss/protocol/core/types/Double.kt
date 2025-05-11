package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.ProtoBE
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE

val ProtoLE.Double by lazy {
    object : ProtoCodec<Double> {
        override fun serialize(value: Double, stream: Sink) {
            stream.writeDoubleLe(value)
        }

        override fun deserialize(stream: Source): Double {
            return stream.readDoubleLe()
        }
    }
}

val ProtoBE.Double by lazy {
    object : ProtoCodec<Double> {
        override fun serialize(value: Double, stream: Sink) {
            stream.writeDouble(value)
        }

        override fun deserialize(stream: Source): Double {
            return stream.readDouble()
        }
    }
}