package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.ProtoBE
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.varlen.types.readUIntVar
import org.chorus_oss.varlen.types.writeUIntVar

val ProtoLE.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Sink) {
            stream.writeUIntLe(value)
        }

        override fun deserialize(stream: Source): UInt {
            return stream.readUIntLe()
        }
    }
}

val ProtoBE.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Sink) {
            stream.writeUInt(value)
        }

        override fun deserialize(stream: Source): UInt {
            return stream.readUInt()
        }
    }
}

val ProtoVAR.UInt by lazy {
    object : ProtoCodec<UInt> {
        override fun serialize(value: UInt, stream: Sink) {
            stream.writeUIntVar(value)
        }

        override fun deserialize(stream: Source): UInt {
            return stream.readUIntVar()
        }
    }
}