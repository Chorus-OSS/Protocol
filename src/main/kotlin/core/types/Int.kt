package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.Zigzag

val Int.Companion.protoCodecLE by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: ByteBuf) {
            stream.writeIntLE(value)
        }

        override fun deserialize(stream: ByteBuf): Int {
            return stream.readIntLE()
        }
    }
}

val Int.Companion.protoCodecBE by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: ByteBuf) {
            stream.writeInt(value)
        }

        override fun deserialize(stream: ByteBuf): Int {
            return stream.readInt()
        }
    }
}

val Int.Companion.protoCodecVAR by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: ByteBuf) {
            UInt.protoCodecVAR.serialize(Int.zigzag(value), stream)
        }

        override fun deserialize(stream: ByteBuf): Int {
            return UInt.zigzag(UInt.protoCodecVAR.deserialize(stream))
        }
    }
}

val Int.Companion.zigzag by lazy {
    Zigzag<Int, UInt> { value ->
        ((value shl 1) xor (value shr 31)).toUInt()
    }
}