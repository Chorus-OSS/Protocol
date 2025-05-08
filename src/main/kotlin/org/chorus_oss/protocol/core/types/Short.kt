package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import kotlinx.io.readShortLe
import kotlinx.io.writeShortLe
import org.chorus_oss.protocol.core.*

val ProtoLE.Short by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: Buffer) {
            stream.writeShortLe(value)
        }

        override fun deserialize(stream: Buffer): Short {
            return stream.readShortLe()
        }
    }
}

val ProtoBE.Short by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: Buffer) {
            stream.writeShort(value)
        }

        override fun deserialize(stream: Buffer): Short {
            return stream.readShort()
        }
    }
}

val ProtoVAR.Short by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: Buffer) {
            ProtoVAR.UShort.serialize(Short.zigzag(value), stream)
        }

        override fun deserialize(stream: Buffer): Short {
            return UShort.zigzag(ProtoVAR.UShort.deserialize(stream))
        }
    }
}

val Short.Companion.zigzag by lazy {
    Zigzag<Short, UShort> { value ->
        ((value.toInt() shl 1) xor (value.toInt() shr 15)).toUShort()
    }
}