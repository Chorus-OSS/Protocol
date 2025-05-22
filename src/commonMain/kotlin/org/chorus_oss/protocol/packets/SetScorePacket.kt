package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.scoreboard.ScoreboardEntry
import org.chorus_oss.protocol.types.scoreboard.ScoreboardRemoveEntry


data class SetScorePacket(
    val actionType: ActionType,
    val modifyEntries: List<ScoreboardEntry>?,
    val removeEntries: List<ScoreboardRemoveEntry>?,
) : Packet(id) {
    companion object : PacketCodec<SetScorePacket> {
        init { PacketRegistry.register(this) }

        enum class ActionType {
            Modify,
            Remove;

            companion object : ProtoCodec<ActionType> {
                override fun serialize(
                    value: ActionType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): ActionType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.SET_SCORE_PACKET

        override fun serialize(value: SetScorePacket, stream: Sink) {
            ActionType.serialize(value.actionType, stream)
            when (value.actionType) {
                ActionType.Modify -> ProtoHelper.serializeList(
                    value.modifyEntries as List<ScoreboardEntry>,
                    stream,
                    ScoreboardEntry
                )

                else -> Unit
            }
            when (value.actionType) {
                ActionType.Remove -> ProtoHelper.serializeList(
                    value.removeEntries as List<ScoreboardRemoveEntry>,
                    stream,
                    ScoreboardRemoveEntry
                )

                else -> Unit
            }
        }

        override fun deserialize(stream: Source): SetScorePacket {
            val actionType: ActionType
            return SetScorePacket(
                actionType = ActionType.deserialize(stream).also { actionType = it },
                modifyEntries = when (actionType) {
                    ActionType.Modify -> ProtoHelper.deserializeList(stream, ScoreboardEntry)
                    else -> null
                },
                removeEntries = when (actionType) {
                    ActionType.Remove -> ProtoHelper.deserializeList(stream, ScoreboardRemoveEntry)
                    else -> null
                }
            )
        }
    }
}
