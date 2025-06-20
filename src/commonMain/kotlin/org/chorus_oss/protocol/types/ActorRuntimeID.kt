package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.ULong
import kotlin.jvm.JvmInline

object ActorRuntimeID : ProtoCodec<ULong> {
    override fun serialize(value: ULong, stream: Sink) {
        ProtoVAR.ULong.serialize(value, stream)
    }

    override fun deserialize(stream: Source): ULong {
        return ProtoVAR.ULong.deserialize(stream)
    }
}