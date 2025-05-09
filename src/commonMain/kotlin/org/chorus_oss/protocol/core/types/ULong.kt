package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readULongVar
import org.chorus_oss.varlen.types.writeULongVar

val ProtoLE.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Buffer) {
            stream.writeULongLe(value)
        }

        override fun deserialize(stream: Buffer): ULong {
            return stream.readULongLe()
        }
    }
}

val ProtoBE.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Buffer) {
            stream.writeULong(value)
        }

        override fun deserialize(stream: Buffer): ULong {
            return stream.readULong()
        }
    }
}

val ProtoVAR.ULong by lazy {
    object : ProtoCodec<ULong> {
        override fun serialize(value: ULong, stream: Buffer) {
            stream.writeULongVar(value)
        }

        override fun deserialize(stream: Buffer): ULong {
            return stream.readULongVar()
        }
    }
}