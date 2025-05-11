package org.chorus_oss.protocol.core.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec

val Proto.Byte by lazy {
    object : ProtoCodec<Byte> {
        override fun serialize(value: Byte, stream: Sink) {
            stream.writeByte(value)
        }

        override fun deserialize(stream: Source): Byte {
            return stream.readByte()
        }
    }
}