package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*

val ProtoLE.UShort by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: Buffer) {
            stream.writeUShortLe(value)
        }

        override fun deserialize(stream: Buffer): UShort {
            return stream.readUShortLe()
        }
    }
}

val ProtoBE.UShort by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: Buffer) {
            stream.writeUShort(value)
        }

        override fun deserialize(stream: Buffer): UShort {
            return stream.readUShort()
        }
    }
}

val ProtoVAR.UShort by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: Buffer) {
            var mValue = value.toUInt()

            while (mValue >= 128u) {
                val next = ((mValue and 127u) or 128u).toByte()
                mValue = mValue shr 7

                stream.writeByte(next)
            }

            stream.writeByte((mValue and 127u).toByte())
        }

        override fun deserialize(stream: Buffer): UShort {
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