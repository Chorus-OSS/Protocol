package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec

val Proto.Boolean by lazy {
    object : ProtoCodec<Boolean> {
        override fun serialize(value: Boolean, stream: Buffer) {
            stream.writeByte(if (value) 1 else 0)
        }

        override fun deserialize(stream: Buffer): Boolean {
            return stream.readByte() == 1.toByte()
        }
    }
}