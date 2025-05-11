package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readULongVar
import org.chorus_oss.varlen.types.writeULongVar

val ProtoLE.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Sink) {
            stream.writeULongLe(value)
        }

        override fun deserialize(stream: Source): ULong {
            return stream.readULongLe()
        }
    }
}

val ProtoBE.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Sink) {
            stream.writeULong(value)
        }

        override fun deserialize(stream: Source): ULong {
            return stream.readULong()
        }
    }
}

val ProtoVAR.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Sink) {
            stream.writeULongVar(value)
        }

        override fun deserialize(stream: Source): ULong {
            return stream.readULongVar()
        }
    }
}