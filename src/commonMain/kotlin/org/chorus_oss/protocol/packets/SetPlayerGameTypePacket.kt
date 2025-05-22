package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.GameType


data class SetPlayerGameTypePacket(
    val gameType: GameType,
) : Packet(id) {
    companion object : PacketCodec<SetPlayerGameTypePacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.SET_PLAYER_GAME_TYPE_PACKET

        override fun serialize(
            value: SetPlayerGameTypePacket,
            stream: Sink
        ) {
            GameType.serialize(value.gameType, stream)
        }

        override fun deserialize(stream: Source): SetPlayerGameTypePacket {
            return SetPlayerGameTypePacket(
                gameType = GameType.deserialize(stream),
            )
        }
    }
}
