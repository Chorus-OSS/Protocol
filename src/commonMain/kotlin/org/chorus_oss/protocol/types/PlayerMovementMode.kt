package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class PlayerMovementMode {
    Client,
    Server,
    ServerWithRewind;

    companion object : ProtoCodec<PlayerMovementMode> {
        override fun serialize(value: PlayerMovementMode, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): PlayerMovementMode {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}