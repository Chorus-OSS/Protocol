package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import kotlinx.io.readLongLe
import kotlinx.io.writeLongLe
import org.chorus_oss.protocol.core.*

val ProtoLE.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Buffer) {
            stream.writeLongLe(value)
        }

        override fun deserialize(stream: Buffer): Long {
            return stream.readLongLe()
        }
    }
}

val ProtoBE.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Buffer) {
            stream.writeLong(value)
        }

        override fun deserialize(stream: Buffer): Long {
            return stream.readLong()
        }
    }
}

val ProtoVAR.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Buffer) {
            ProtoVAR.ULong.serialize(Long.zigzag(value), stream)
        }

        override fun deserialize(stream: Buffer): Long {
            return ULong.zigzag(ProtoVAR.ULong.deserialize(stream))
        }
    }
}

val Long.Companion.zigzag by lazy {
    Zigzag<Long, ULong> { value ->
        ((value shl 1) xor (value shr 63)).toULong()
    }
}