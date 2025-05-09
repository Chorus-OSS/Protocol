package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import kotlinx.io.readShortLe
import kotlinx.io.writeShortLe
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readShortVar
import org.chorus_oss.varlen.types.writeShortVar

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
            stream.writeShortVar(value)
        }

        override fun deserialize(stream: Buffer): Short {
            return stream.readShortVar()
        }
    }
}