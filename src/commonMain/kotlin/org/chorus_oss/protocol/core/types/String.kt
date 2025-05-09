package org.chorus_oss.protocol.core.types

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR

val Proto.String by lazy {
    object : ProtoCodec<String> {
        override fun serialize(value: String, stream: Buffer) {
            val bytes = value.encodeToByteArray()
            ProtoVAR.UInt.serialize(bytes.size.toUInt(), stream)
            for (byte in bytes) {
                Proto.Byte.serialize(byte, stream)
            }
        }

        override fun deserialize(stream: Buffer): String {
            val bytes = ByteArray(ProtoVAR.UInt.deserialize(stream).toInt()) {
                Proto.Byte.deserialize(stream)
            }
            return bytes.decodeToString()
        }
    }
}