package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class MultiplayerSettingsPacket(
    val actionType: ActionType,
) : Packet(id) {
    companion object : PacketCodec<MultiplayerSettingsPacket> {
        enum class ActionType {
            EnableMultiplayer,
            DisableMultiplayer,
            RefreshJoinCode;

            companion object : ProtoCodec<ActionType> {
                override fun serialize(
                    value: ActionType,
                    stream: Sink
                ) {
                    ProtoVAR.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): ActionType {
                    return entries[ProtoVAR.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int = 139

        override fun serialize(
            value: MultiplayerSettingsPacket,
            stream: Sink
        ) {
            ActionType.serialize(value.actionType, stream)
        }

        override fun deserialize(stream: Source): MultiplayerSettingsPacket {
            return MultiplayerSettingsPacket(
                actionType = ActionType.deserialize(stream),
            )
        }
    }
}
