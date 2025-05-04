package org.chorus_oss.protocol.core

import io.netty.buffer.ByteBuf

interface ProtoCodec<T> {
    fun serialize(value: T, stream: ByteBuf)

    fun deserialize(stream: ByteBuf): T
}