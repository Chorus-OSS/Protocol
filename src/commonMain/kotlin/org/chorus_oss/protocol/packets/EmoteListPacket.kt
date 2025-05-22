package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Uuid
import org.chorus_oss.protocol.types.ActorRuntimeID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
data class EmoteListPacket(
    val playerRuntimeID: ActorRuntimeID,
    val emotePieces: List<Uuid>,
) : Packet(id) {
    companion object : PacketCodec<EmoteListPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.EMOTE_LIST_PACKET

        override fun serialize(value: EmoteListPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.playerRuntimeID, stream)
            ProtoHelper.serializeList(value.emotePieces, stream, Proto.Uuid)
        }

        override fun deserialize(stream: Source): EmoteListPacket {
            return EmoteListPacket(
                playerRuntimeID = ActorRuntimeID.deserialize(stream),
                emotePieces = ProtoHelper.deserializeList(stream, Proto.Uuid)
            )
        }
    }
}
