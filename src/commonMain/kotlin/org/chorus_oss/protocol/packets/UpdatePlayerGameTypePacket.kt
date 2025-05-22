package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.ULong
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.GameType


data class UpdatePlayerGameTypePacket(
    val gameType: GameType,
    val playerUniqueID: ActorUniqueID,
    val tick: ULong,
) : Packet(id) {
    companion object : PacketCodec<UpdatePlayerGameTypePacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.UPDATE_PLAYER_GAME_TYPE_PACKET

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
