package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Long
import kotlin.Long
import kotlin.jvm.JvmInline

object ActorUniqueID : ProtoCodec<Long> {
    override fun serialize(value: Long, stream: Sink) {
        ProtoVAR.Long.serialize(value, stream)
    }

    override fun deserialize(stream: Source): Long {
        return ProtoVAR.Long.deserialize(stream)
    }
}