package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.ProtoBE
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE

val ProtoLE.Float by lazy {
    object : ProtoCodec<Float> {
        override fun serialize(value: Float, stream: Sink) {
            stream.writeFloatLe(value)
        }

        override fun deserialize(stream: Source): Float {
            return stream.readFloatLe()
        }
    }
}

val ProtoBE.Float by lazy {
    object : ProtoCodec<Float> {
        override fun serialize(value: Float, stream: Sink) {
            stream.writeFloat(value)
        }

        override fun deserialize(stream: Source): Float {
            return stream.readFloat()
        }
    }
}