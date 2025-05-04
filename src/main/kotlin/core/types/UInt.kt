package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.Zigzag

val UInt.Companion.protoCodecLE by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: ByteBuf) {
            stream.writeIntLE(value.toInt())
        }

        override fun deserialize(stream: ByteBuf): UInt {
            return stream.readIntLE().toUInt()
        }
    }
}

val UInt.Companion.protoCodecBE by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: ByteBuf) {
            stream.writeInt(value.toInt())
        }

        override fun deserialize(stream: ByteBuf): UInt {
            return stream.readInt().toUInt()
        }
    }
}

val UInt.Companion.protoCodecVAR by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: ByteBuf) {
            var mValue = value

            while (mValue >= 128u) {
                val next = ((mValue and 127u) or 128u).toByte()
                mValue = mValue shr 7

                stream.writeByte(next.toInt())
            }

            stream.writeByte((mValue and 127u).toInt())
        }

        override fun deserialize(stream: ByteBuf): UInt {
            var shift = 0
            var decoded = 0u
            var next: Byte

            while (true) {
                next = stream.readByte()
                decoded = decoded or ((next.toUInt() and 127u) shl shift)

                if (next.toInt() and 128 != 0) {
                    shift += 7
                }
                else return decoded
            }
        }
    }
}

val UInt.Companion.zigzag by lazy {
    Zigzag<UInt, Int> { value ->
        (value.toInt() shr 1) xor -(value.toInt() and 1)
    }
}