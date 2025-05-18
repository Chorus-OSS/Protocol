package org.chorus_oss.protocol.types.scoreboard

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.core.types.String

data class ScoreboardRemoveEntry(
    val entryID: Long,
    val objectiveName: String,
    val score: Int,
) {
    companion object : ProtoCodec<ScoreboardRemoveEntry> {
        override fun serialize(value: ScoreboardRemoveEntry, stream: Sink) {
            ProtoVAR.Long.serialize(value.entryID, stream)
            Proto.String.serialize(value.objectiveName, stream)
            ProtoVAR.Int.serialize(value.score, stream)
        }

        override fun deserialize(stream: Source): ScoreboardRemoveEntry {
            return ScoreboardRemoveEntry(
                entryID = ProtoVAR.Long.deserialize(stream),
                objectiveName = Proto.String.deserialize(stream),
                score = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
