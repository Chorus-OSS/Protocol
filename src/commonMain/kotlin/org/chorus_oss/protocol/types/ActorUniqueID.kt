package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Long
import kotlin.jvm.JvmInline

@JvmInline
value class ActorUniqueID(val id: Long) {
    companion object : ProtoCodec<ActorUniqueID> {
        override fun serialize(value: ActorUniqueID, stream: Sink) {
            ProtoVAR.Long.serialize(value.id, stream)
        }

        override fun deserialize(stream: Source): ActorUniqueID {
            return ActorUniqueID(ProtoVAR.Long.deserialize(stream))
        }
    }
}