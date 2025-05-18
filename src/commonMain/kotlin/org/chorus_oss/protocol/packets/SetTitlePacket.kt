package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String


data class SetTitlePacket(
    val actionType: ActionType,
    val text: String,
    val fadeInDuration: Int,
    val remainDuration: Int,
    val fadeOutDuration: Int,
    val xuid: String,
    val platformOnlineID: String,
    val filteredMessage: String,
) : Packet(id) {
    companion object : PacketCodec<SetTitlePacket> {
        enum class ActionType {
            Clear,
            Reset,
            SetTitle,
            SetSubtitle,
            SetActionbar,
            SetDurations,
            SetTitleJSON,
            SetSubtitleJSON,
            SetActionbarJSON;

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

        override val id: Int
            get() = ProtocolInfo.SET_TITLE_PACKET

        override fun serialize(value: SetTitlePacket, stream: Sink) {
            ActionType.serialize(value.actionType, stream)
            Proto.String.serialize(value.text, stream)
            ProtoVAR.Int.serialize(value.fadeInDuration, stream)
            ProtoVAR.Int.serialize(value.remainDuration, stream)
            ProtoVAR.Int.serialize(value.fadeOutDuration, stream)
            Proto.String.serialize(value.xuid, stream)
            Proto.String.serialize(value.platformOnlineID, stream)
            Proto.String.serialize(value.filteredMessage, stream)
        }

        override fun deserialize(stream: Source): SetTitlePacket {
            return SetTitlePacket(
                actionType = ActionType.deserialize(stream),
                text = Proto.String.deserialize(stream),
                fadeInDuration = ProtoVAR.Int.deserialize(stream),
                remainDuration = ProtoVAR.Int.deserialize(stream),
                fadeOutDuration = ProtoVAR.Int.deserialize(stream),
                xuid = Proto.String.deserialize(stream),
                platformOnlineID = Proto.String.deserialize(stream),
                filteredMessage = Proto.String.deserialize(stream),
            )
        }
    }
}
