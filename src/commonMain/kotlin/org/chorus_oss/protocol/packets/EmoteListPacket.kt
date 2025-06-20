package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
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
    val playerRuntimeID: ULong,
    val emotePieces: List<Uuid>,
) : Packet(id) {
    companion object : PacketCodec<EmoteListPacket> {
        override val id: Int = 152

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
