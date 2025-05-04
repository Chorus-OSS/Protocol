package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.Zigzag

val Short.Companion.protoCodecLE by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: ByteBuf) {
            stream.writeShortLE(value.toInt())
        }

        override fun deserialize(stream: ByteBuf): Short {
            return stream.readShortLE()
        }
    }
}

val Short.Companion.protoCodecBE by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: ByteBuf) {
            stream.writeShort(value.toInt())
        }

        override fun deserialize(stream: ByteBuf): Short {
            return stream.readShort()
        }
    }
}

val Short.Companion.protoCodecVAR by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: ByteBuf) {
            UShort.protoCodecVAR.serialize(Short.zigzag(value), stream)
        }

        override fun deserialize(stream: ByteBuf): Short {
            return UShort.zigzag(UShort.protoCodecVAR.deserialize(stream))
        }
    }
}

val Short.Companion.zigzag by lazy {
    Zigzag<Short, UShort> { value ->
        ((value.toInt() shl 1) xor (value.toInt() shr 15)).toUShort()
    }
}