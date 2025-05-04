package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.Zigzag

val Long.Companion.protoCodecLE by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: ByteBuf) {
            stream.writeLongLE(value)
        }

        override fun deserialize(stream: ByteBuf): Long {
            return stream.readLongLE()
        }
    }
}

val Long.Companion.protoCodecBE by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: ByteBuf) {
            stream.writeLong(value)
        }

        override fun deserialize(stream: ByteBuf): Long {
            return stream.readLong()
        }
    }
}

val Long.Companion.protoCodecVAR by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: ByteBuf) {
            ULong.protoCodecVAR.serialize(Long.zigzag(value), stream)
        }

        override fun deserialize(stream: ByteBuf): Long {
            return ULong.zigzag(ULong.protoCodecVAR.deserialize(stream))
        }
    }
}

val Long.Companion.zigzag by lazy {
    Zigzag<Long, ULong> { value ->
        ((value shl 1) xor (value shr 63)).toULong()
    }
}