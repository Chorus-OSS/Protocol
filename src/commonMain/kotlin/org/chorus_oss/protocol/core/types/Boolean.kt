package org.chorus_oss.protocol.core.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec

val Proto.Boolean by lazy {
    object : ProtoCodec<Boolean> {
        override fun serialize(value: Boolean, stream: Sink) {
            stream.writeByte(if (value) 1 else 0)
        }

        override fun deserialize(stream: Source): Boolean {
            return stream.readByte() == 1.toByte()
        }
    }
}