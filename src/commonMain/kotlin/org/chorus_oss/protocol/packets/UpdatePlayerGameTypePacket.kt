package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.GameType


data class UpdatePlayerGameTypePacket(
    val gameType: GameType,
    val playerUniqueID: Long,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<UpdatePlayerGameTypePacket> {
        override val id: Int = 151

        override fun serialize(
            value: UpdatePlayerGameTypePacket,
            stream: Sink
        ) {
            GameType.serialize(value.gameType, stream)
            ActorUniqueID.serialize(value.playerUniqueID, stream)
            ProtoVAR.ULong.serialize(value.tick, stream)
        }

        override fun deserialize(stream: Source): UpdatePlayerGameTypePacket {
            return UpdatePlayerGameTypePacket(
                gameType = GameType.deserialize(stream),
                playerUniqueID = ActorUniqueID.deserialize(stream),
                tick = ProtoVAR.ULong.deserialize(stream),
            )
        }
    }
}
