package org.chorus_oss.protocol.core.types

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import kotlinx.io.readByteString
import kotlinx.io.write
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR

val Proto.ByteString by lazy {
    object : ProtoCodec<ByteString> {
        override fun serialize(value: ByteString, stream: Sink) {
            ProtoVAR.Int.serialize(value.size, stream)
            stream.write(value)
        }

        override fun deserialize(stream: Source): ByteString {
            val size = ProtoVAR.Int.deserialize(stream)
            return stream.readByteString(size)
        }
    }
}