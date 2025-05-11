package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.ULong
import kotlin.jvm.JvmInline

@JvmInline
value class ActorRuntimeID(val id: ULong) {
    companion object : ProtoCodec<ActorRuntimeID> {
        override fun serialize(value: ActorRuntimeID, stream: Sink) {
            ProtoVAR.ULong.serialize(value.id, stream)
        }

        override fun deserialize(stream: Source): ActorRuntimeID {
            return ActorRuntimeID(ProtoVAR.ULong.deserialize(stream))
        }
    }
}