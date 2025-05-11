package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readLongVar
import org.chorus_oss.varlen.types.writeLongVar

val ProtoLE.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Sink) {
            stream.writeLongLe(value)
        }

        override fun deserialize(stream: Source): Long {
            return stream.readLongLe()
        }
    }
}

val ProtoBE.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Sink) {
            stream.writeLong(value)
        }

        override fun deserialize(stream: Source): Long {
            return stream.readLong()
        }
    }
}

val ProtoVAR.Long by lazy {
    object : ProtoCodec<Long> {
        override fun serialize(value: Long, stream: Sink) {
            stream.writeLongVar(value)
        }

        override fun deserialize(stream: Source): Long {
            return stream.readLongVar()
        }
    }
}