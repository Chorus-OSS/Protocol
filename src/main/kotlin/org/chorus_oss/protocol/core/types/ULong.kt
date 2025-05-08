package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*

val ProtoLE.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Buffer) {
            stream.writeULongLe(value)
        }

        override fun deserialize(stream: Buffer): ULong {
            return stream.readULongLe()
        }
    }
}

val ProtoBE.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Buffer) {
            stream.writeULong(value)
        }

        override fun deserialize(stream: Buffer): ULong {
            return stream.readULong()
        }
    }
}

val ProtoVAR.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Buffer) {
            var mValue = value

            while (mValue >= 128u) {
                val next = ((mValue and 127u) or 128u).toByte()
                mValue = mValue shr 7

                stream.writeByte(next)
            }

            stream.writeByte((mValue and 127u).toByte())
        }

        override fun deserialize(stream: Buffer): ULong {
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