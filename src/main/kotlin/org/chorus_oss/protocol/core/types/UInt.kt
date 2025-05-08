package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*

val ProtoLE.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Buffer) {
            stream.writeUIntLe(value)
        }

        override fun deserialize(stream: Buffer): UInt {
            return stream.readUIntLe()
        }
    }
}

val ProtoBE.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Buffer) {
            stream.writeUInt(value)
        }

        override fun deserialize(stream: Buffer): UInt {
            return stream.readUInt()
        }
    }
}

val ProtoVAR.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Buffer) {
            var mValue = value

            while (mValue >= 128u) {
                val next = ((mValue and 127u) or 128u).toByte()
                mValue = mValue shr 7

                stream.writeByte(next)
            }

            stream.writeByte((mValue and 127u).toByte())
        }

        override fun deserialize(stream: Buffer): UInt {
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