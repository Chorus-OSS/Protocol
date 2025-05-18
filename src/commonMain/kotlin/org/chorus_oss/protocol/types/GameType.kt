package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class GameType {
    Survival,
    Creative,
    Adventure,
    SurvivalSpectator,
    CreativeSpectator,
    Default,
    Spectator;

    companion object : ProtoCodec<GameType> {
        override fun serialize(value: GameType, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): GameType {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}
