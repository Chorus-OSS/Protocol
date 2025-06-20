package org.chorus_oss.protocol.core.types

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readByteArray
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR

val Proto.String by lazy {
    object : ProtoCodec<String> {
        override fun serialize(value: String, stream: Sink) {
            val bytes = value.encodeToByteArray()
            ProtoVAR.UInt.serialize(bytes.size.toUInt(), stream)
            stream.write(bytes)
        }

        override fun deserialize(stream: Source): String {
            val size = ProtoVAR.UInt.deserialize(stream).toInt()
            val bytes = stream.readByteArray(size)
            return bytes.decodeToString()
        }
    }
}