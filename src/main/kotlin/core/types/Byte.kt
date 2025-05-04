package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec

val Byte.Companion.protoCodec by lazy {
    object : ProtoCodec<Byte> {
        override fun serialize(value: Byte, stream: ByteBuf) {
            stream.writeByte(value.toInt())
        }

        override fun deserialize(stream: ByteBuf): Byte {
            return stream.readByte()
        }
    }
}