package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ULong

data class CacheBlob(
    val hash: ULong,
    val payload: List<Byte>,
) {
    companion object : ProtoCodec<CacheBlob> {
        override fun serialize(value: CacheBlob, stream: Sink) {
            ProtoLE.ULong.serialize(value.hash, stream)
            ProtoHelper.serializeList(value.payload, stream, Proto.Byte)
        }

        override fun deserialize(stream: Source): CacheBlob {
            return CacheBlob(
                hash = ProtoLE.ULong.deserialize(stream),
                payload = ProtoHelper.deserializeList(stream, Proto.Byte)
            )
        }
    }
}
