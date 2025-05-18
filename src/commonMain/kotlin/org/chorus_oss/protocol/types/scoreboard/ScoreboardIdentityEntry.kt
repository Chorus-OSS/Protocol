package org.chorus_oss.protocol.types.scoreboard

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.types.ActorUniqueID

data class ScoreboardIdentityEntry(
    val entryID: Long,
    val entityUniqueID: ActorUniqueID,
) {
    companion object : ProtoCodec<ScoreboardIdentityEntry> {
        override fun serialize(
            value: ScoreboardIdentityEntry,
            stream: Sink
        ) {
            ProtoVAR.Long.serialize(value.entryID, stream)
            ActorUniqueID.serialize(value.entityUniqueID, stream)
        }

        override fun deserialize(stream: Source): ScoreboardIdentityEntry {
            return ScoreboardIdentityEntry(
                entryID = ProtoVAR.Long.deserialize(stream),
                entityUniqueID = ActorUniqueID.deserialize(stream),
            )
        }
    }
}
