package org.chorus_oss.protocol.core.types

import kotlinx.io.*
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readIntVar
import org.chorus_oss.varlen.types.writeIntVar

val ProtoLE.Int by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: Sink) {
            stream.writeIntLe(value)
        }

        override fun deserialize(stream: Source): Int {
            return stream.readIntLe()
        }
    }
}

val ProtoBE.Int by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: Sink) {
            stream.writeInt(value)
        }

        override fun deserialize(stream: Source): Int {
            return stream.readInt()
        }
    }
}

val ProtoVAR.Int by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: Sink) {
            stream.writeIntVar(value)
        }

        override fun deserialize(stream: Source): Int {
            return stream.readIntVar()
        }
    }
}