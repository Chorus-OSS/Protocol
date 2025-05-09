package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import kotlinx.io.readLongLe
import kotlinx.io.writeLongLe
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readLongVar
import org.chorus_oss.varlen.types.writeLongVar

val ProtoLE.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Buffer) {
            stream.writeLongLe(value)
        }

        override fun deserialize(stream: Buffer): Long {
            return stream.readLongLe()
        }
    }
}

val ProtoBE.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Buffer) {
            stream.writeLong(value)
        }

        override fun deserialize(stream: Buffer): Long {
            return stream.readLong()
        }
    }
}

val ProtoVAR.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Buffer) {
            stream.writeLongVar(value)
        }

        override fun deserialize(stream: Buffer): Long {
            return stream.readLongVar()
        }
    }
}