package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec

val UByte.Companion.protoCodec by lazy {
    object : ProtoCodec<UByte> {
        override fun serialize(value: UByte, stream: ByteBuf) {
            stream.writeByte(value.toInt())
        }

        override fun deserialize(stream: ByteBuf): UByte {
            return stream.readByte().toUByte()
        }
    }
}