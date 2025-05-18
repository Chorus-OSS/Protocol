package org.chorus_oss.protocol.types.scoreboard

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Long

data class ScoreboardIdentityClearEntry(
    val entryID: Long,
) {
    companion object : ProtoCodec<ScoreboardIdentityClearEntry> {
        override fun serialize(
            value: ScoreboardIdentityClearEntry,
            stream: Sink
        ) {
            ProtoVAR.Long.serialize(value.entryID, stream)
        }

        override fun deserialize(stream: Source): ScoreboardIdentityClearEntry {
            return ScoreboardIdentityClearEntry(
                entryID = ProtoVAR.Long.deserialize(stream)
            )
        }
    }
}
