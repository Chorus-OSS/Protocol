package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.ActorRuntimeID


data class ShowCreditsPacket(
    val playerRuntimeID: ActorRuntimeID,
    val statusType: StatusType,
) : Packet(id) {
    companion object : PacketCodec<ShowCreditsPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class StatusType {
            Start,
            End;

            companion object : ProtoCodec<StatusType> {
                override fun serialize(
                    value: StatusType,
                    stream: Sink
                ) {
                    ProtoVAR.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): StatusType {
                    return entries[ProtoVAR.Int.deserialize(stream)]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.SHOW_CREDITS_PACKET

        override fun serialize(value: ShowCreditsPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.playerRuntimeID, stream)
            StatusType.serialize(value.statusType, stream)
        }

        override fun deserialize(stream: Source): ShowCreditsPacket {
            return ShowCreditsPacket(
                playerRuntimeID = ActorRuntimeID.deserialize(stream),
                statusType = StatusType.deserialize(stream),
            )
        }
    }
}
