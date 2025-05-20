package org.chorus_oss.protocol.core.types

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readUByte
import kotlinx.io.writeUByte
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec

val Proto.UByte by lazy {
    object : ProtoCodec<UByte> {
        override fun serialize(value: UByte, stream: Sink) {
            stream.writeUByte(value)
        }

        override fun deserialize(stream: Source): UByte {
            return stream.readUByte()
        }
    }
}