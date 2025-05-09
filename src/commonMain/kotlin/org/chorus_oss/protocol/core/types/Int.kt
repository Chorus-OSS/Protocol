package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import kotlinx.io.readIntLe
import kotlinx.io.writeIntLe
import org.chorus_oss.protocol.core.*
import org.chorus_oss.varlen.types.readIntVar
import org.chorus_oss.varlen.types.writeIntVar

val ProtoLE.Int by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: Buffer) {
            stream.writeIntLe(value)
        }

        override fun deserialize(stream: Buffer): Int {
            return stream.readIntLe()
        }
    }
}

val ProtoBE.Int by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: Buffer) {
            stream.writeInt(value)
        }

        override fun deserialize(stream: Buffer): Int {
            return stream.readInt()
        }
    }
}

val ProtoVAR.Int by lazy {
    object : ProtoCodec<Int> {
        override fun serialize(value: Int, stream: Buffer) {
            stream.writeIntVar(value)
        }

        override fun deserialize(stream: Buffer): Int {
            return stream.readIntVar()
        }
    }
}