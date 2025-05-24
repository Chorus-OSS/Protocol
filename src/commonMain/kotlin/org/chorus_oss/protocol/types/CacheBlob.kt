package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.core.types.ULong

data class CacheBlob(
    val hash: ULong,
    val payload: ByteString,
) {
    companion object : ProtoCodec<CacheBlob> {
        override fun serialize(value: CacheBlob, stream: Sink) {
            ProtoLE.ULong.serialize(value.hash, stream)
            Proto.ByteString.serialize(value.payload, stream)
        }

        override fun deserialize(stream: Source): CacheBlob {
            return CacheBlob(
                hash = ProtoLE.ULong.deserialize(stream),
                payload = Proto.ByteString.deserialize(stream),
            )
        }
    }
}
