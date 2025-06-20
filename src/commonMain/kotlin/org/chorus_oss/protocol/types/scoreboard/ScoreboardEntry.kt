package org.chorus_oss.protocol.types.scoreboard

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.ActorUniqueID

data class ScoreboardEntry(
    val entryID: Long,
    val objectiveName: String,
    val score: Int,
    val identityType: IdentityType,
    val entityUniqueID: Long?,
    val displayName: String?,
) {
    companion object : ProtoCodec<ScoreboardEntry> {
        enum class IdentityType(val net: Byte) {
            Player(1),
            Entity(2),
            FakePlayer(3);

            companion object : ProtoCodec<IdentityType> {
                override fun serialize(
                    value: IdentityType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): IdentityType {
                    return Proto.Byte.deserialize(stream).let {
                        entries.find { e -> e.net == it }!!
                    }
                }
            }
        }

        override fun serialize(
            value: ScoreboardEntry,
            stream: Sink
        ) {
            ProtoVAR.Long.serialize(value.entryID, stream)
            Proto.String.serialize(value.objectiveName, stream)
            ProtoVAR.Int.serialize(value.score, stream)
            IdentityType.serialize(value.identityType, stream)
            when (value.identityType) {
                IdentityType.Player,
                IdentityType.Entity -> ActorUniqueID.serialize(value.entityUniqueID as Long, stream)

                else -> Unit
            }
            when (value.identityType) {
                IdentityType.FakePlayer -> Proto.String.serialize(value.displayName as String, stream)
                else -> Unit
            }
        }

        override fun deserialize(stream: Source): ScoreboardEntry {
            val identityType: IdentityType
            return ScoreboardEntry(
                entryID = ProtoVAR.Long.deserialize(stream),
                objectiveName = Proto.String.deserialize(stream),
                score = ProtoVAR.Int.deserialize(stream),
                identityType = IdentityType.deserialize(stream).also { identityType = it },
                entityUniqueID = when (identityType) {
                    IdentityType.Player,
                    IdentityType.Entity -> ActorUniqueID.deserialize(stream)

                    else -> null
                },
                displayName = when (identityType) {
                    IdentityType.FakePlayer -> Proto.String.deserialize(stream)
                    else -> null
                }
            )
        }
    }
}
