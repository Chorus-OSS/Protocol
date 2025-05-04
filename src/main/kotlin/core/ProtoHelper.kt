package org.chorus_oss.protocol.core

import io.netty.buffer.ByteBuf
import org.chorus_oss.protocol.core.types.protoCodec

object ProtoHelper {
    fun <T> writeNullable(value: T?, stream: ByteBuf, fn: (T, ByteBuf) -> Unit) {
        Boolean.protoCodec.serialize(value != null, stream)
        if (value != null) {
            fn.invoke(value, stream)
        }
    }

    fun <T> readNullable(stream: ByteBuf, fn: (ByteBuf) -> T): T? {
        val present = Boolean.protoCodec.deserialize(stream)
        if (present) {
            return fn.invoke(stream)
        }
        return null
    }
}