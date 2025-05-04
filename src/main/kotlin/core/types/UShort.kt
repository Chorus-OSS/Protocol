package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.Zigzag

val UShort.Companion.protoCodecLE by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: ByteBuf) {
            stream.writeShortLE(value.toInt())
        }

        override fun deserialize(stream: ByteBuf): UShort {
            return stream.readShortLE().toUShort()
        }
    }
}

val UShort.Companion.protoCodecBE by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: ByteBuf) {
            stream.writeShort(value.toInt())
        }

        override fun deserialize(stream: ByteBuf): UShort {
            return stream.readShort().toUShort()
        }
    }
}

val UShort.Companion.protoCodecVAR by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: ByteBuf) {
            var mValue = value.toUInt()

            while (mValue >= 128u) {
                val next = ((mValue and 127u) or 128u).toByte()
                mValue = mValue shr 7

                stream.writeByte(next.toInt())
            }

            stream.writeByte((mValue and 127u).toInt())
        }

        override fun deserialize(stream: ByteBuf): UShort {
            var shift = 0
            var decoded = 0u
            var next: Byte

            while (true) {
                next = stream.readByte()
                decoded = decoded or ((next.toUInt() and 127u) shl shift)

                if (next.toInt() and 128 != 0) {
                    shift += 7
                }
                else return decoded.toUShort()
            }
        }
    }
}

val UShort.Companion.zigzag by lazy {
    Zigzag<UShort, Short> { value ->
        ((value.toInt() shr 1) xor -(value.toInt() and 1)).toShort()
    }
}