package org.chorus_oss.protocol.core.types

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readLongLe
import kotlinx.io.writeLongLe
import org.chorus_oss.protocol.core.ProtoBE
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
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