package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.scoreboard.ScoreboardIdentityClearEntry
import org.chorus_oss.protocol.types.scoreboard.ScoreboardIdentityEntry


data class SetScoreboardIdentityPacket(
    val actionType: ActionType,
    val registerEntries: List<ScoreboardIdentityEntry>?,
    val clearEntries: List<ScoreboardIdentityClearEntry>?,
) : Packet(id) {
    companion object : PacketCodec<SetScoreboardIdentityPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class ActionType {
            Register,
            Clear;

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
            get() = ProtocolInfo.SET_SCOREBOARD_IDENTITY_PACKET

        override fun serialize(
            value: SetScoreboardIdentityPacket,
            stream: Sink
        ) {
            ActionType.serialize(value.actionType, stream)
            when (value.actionType) {
                ActionType.Register -> ProtoHelper.serializeList(
                    value.registerEntries as List<ScoreboardIdentityEntry>, stream,
                    ScoreboardIdentityEntry
                )

                else -> Unit
            }
            when (value.actionType) {
                ActionType.Clear -> ProtoHelper.serializeList(
                    value.clearEntries as List<ScoreboardIdentityClearEntry>, stream,
                    ScoreboardIdentityClearEntry
                )

                else -> Unit
            }
        }

        override fun deserialize(stream: Source): SetScoreboardIdentityPacket {
            val actionType: ActionType
            return SetScoreboardIdentityPacket(
                actionType = ActionType.deserialize(stream).also { actionType = it },
                registerEntries = when (actionType) {
                    ActionType.Register -> ProtoHelper.deserializeList(stream, ScoreboardIdentityEntry)
                    else -> null
                },
                clearEntries = when (actionType) {
                    ActionType.Clear -> ProtoHelper.deserializeList(stream, ScoreboardIdentityClearEntry)
                    else -> null
                }
            )
        }
    }
}
