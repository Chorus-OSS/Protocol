package org.chorus_oss.protocol.core.types

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.ProtoCodec

val Boolean.Companion.protoCodec by lazy {
    object : ProtoCodec<Boolean> {
        override fun serialize(value: Boolean, stream: ByteBuf) {
            stream.writeBoolean(value)
        }

        override fun deserialize(stream: ByteBuf): Boolean {
            return stream.readBoolean()
        }
    }
}