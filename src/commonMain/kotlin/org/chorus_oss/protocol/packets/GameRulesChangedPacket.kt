package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.GameRule


data class GameRulesChangedPacket(
    val gameRules: List<GameRule<*>>
) : Packet(id) {
    companion object : PacketCodec<GameRulesChangedPacket> {
        override val id: Int = 72

        override fun serialize(value: GameRulesChangedPacket, stream: Sink) {
            ProtoHelper.serializeList(value.gameRules, stream) { value, stream -> GameRule.serialize(value, stream) }
        }

        override fun deserialize(stream: Source): GameRulesChangedPacket {
            return GameRulesChangedPacket(
                gameRules = ProtoHelper.deserializeList(stream) { stream -> GameRule.deserialize(stream) }
            )
        }
    }
}
