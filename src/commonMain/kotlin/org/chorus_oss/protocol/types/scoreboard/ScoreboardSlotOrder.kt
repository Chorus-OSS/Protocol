package org.chorus_oss.protocol.types.scoreboard

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class ScoreboardSlotOrder {
    Ascending,
    Descending;

    companion object : ProtoCodec<ScoreboardSlotOrder> {
        override fun serialize(
            value: ScoreboardSlotOrder,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): ScoreboardSlotOrder {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}