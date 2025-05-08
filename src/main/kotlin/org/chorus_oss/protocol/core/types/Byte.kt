package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec

val Proto.Byte by lazy {
    object : ProtoCodec<Byte> {
        override fun serialize(value: Byte, stream: Buffer) {
            stream.writeByte(value)
        }

        override fun deserialize(stream: Buffer): Byte {
            return stream.readByte()
        }
    }
}