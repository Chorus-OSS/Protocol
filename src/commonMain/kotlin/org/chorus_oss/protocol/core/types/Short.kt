package org.chorus_oss.protocol.core.types

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readShortLe
import kotlinx.io.writeShortLe
import org.chorus_oss.protocol.core.ProtoBE
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.varlen.types.readShortVar
import org.chorus_oss.varlen.types.writeShortVar

val ProtoLE.Short by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: Sink) {
            stream.writeShortLe(value)
        }

        override fun deserialize(stream: Source): Short {
            return stream.readShortLe()
        }
    }
}

val ProtoBE.Short by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: Sink) {
            stream.writeShort(value)
        }

        override fun deserialize(stream: Source): Short {
            return stream.readShort()
        }
    }
}

val ProtoVAR.Short by lazy {
    object : ProtoCodec<Short> {
        override fun serialize(value: Short, stream: Sink) {
            stream.writeShortVar(value)
        }

        override fun deserialize(stream: Source): Short {
            return stream.readShortVar()
        }
    }
}