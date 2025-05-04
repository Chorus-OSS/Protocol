package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.Zigzag

val ULong.Companion.protoCodecLE by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: ByteBuf) {
            stream.writeLongLE(value.toLong())
        }

        override fun deserialize(stream: ByteBuf): ULong {
            return stream.readLongLE().toULong()
        }
    }
}

val ULong.Companion.protoCodecBE by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: ByteBuf) {
            stream.writeLong(value.toLong())
        }

        override fun deserialize(stream: ByteBuf): ULong {
            return stream.readLong().toULong()
        }
    }
}

val ULong.Companion.protoCodecVAR by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: ByteBuf) {
            var mValue = value

            while (mValue >= 128u) {
                val next = ((mValue and 127u) or 128u).toByte()
                mValue = mValue shr 7

                stream.writeByte(next.toInt())
            }

            stream.writeByte((mValue and 127u).toInt())
        }

        override fun deserialize(stream: ByteBuf): ULong {
            var shift = 0
            var decoded: ULong = 0u
            var next: Byte

            while (true) {
                next = stream.readByte()
                decoded = decoded or ((next.toULong() and 127u) shl shift)

                if (next.toInt() and 128 != 0) {
                    shift += 7
                }
                else return decoded
            }
        }
    }
}

val ULong.Companion.zigzag by lazy {
    Zigzag<ULong, Long> { value ->
        (value.toLong() shr 1) xor -(value.toLong() and 1)
    }
}