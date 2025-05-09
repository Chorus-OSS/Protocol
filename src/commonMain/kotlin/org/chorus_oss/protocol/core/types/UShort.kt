package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readUShortVar
import org.chorus_oss.varlen.types.writeUShortVar

val ProtoLE.UShort by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: Buffer) {
            stream.writeUShortLe(value)
        }

        override fun deserialize(stream: Buffer): UShort {
            return stream.readUShortLe()
        }
    }
}

val ProtoBE.UShort by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: Buffer) {
            stream.writeUShort(value)
        }

        override fun deserialize(stream: Buffer): UShort {
            return stream.readUShort()
        }
    }
}

val ProtoVAR.UShort by lazy {
    object : ProtoCodec<UShort> {
        override fun serialize(value: UShort, stream: Buffer) {
            stream.writeUShortVar(value)
        }

        override fun deserialize(stream: Buffer): UShort {
            return stream.readUShortVar()
        }
    }
}