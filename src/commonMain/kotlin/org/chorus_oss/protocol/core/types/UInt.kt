package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readUIntVar
import org.chorus_oss.varlen.types.writeUIntVar

val ProtoLE.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Buffer) {
            stream.writeUIntLe(value)
        }

        override fun deserialize(stream: Buffer): UInt {
            return stream.readUIntLe()
        }
    }
}

val ProtoBE.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Buffer) {
            stream.writeUInt(value)
        }

        override fun deserialize(stream: Buffer): UInt {
            return stream.readUInt()
        }
    }
}

val ProtoVAR.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Buffer) {
            stream.writeUIntVar(value)
        }

        override fun deserialize(stream: Buffer): UInt {
            return stream.readUIntVar()
        }
    }
}