package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String

data class GameTestResultsPacket(
    val succeeded: Boolean,
    val error: String,
    val name: String,
) : Packet(id) {
    companion object : PacketCodec<GameTestResultsPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.GAME_TEST_RESULTS_PACKET

        override fun serialize(value: GameTestResultsPacket, stream: Sink) {
            Proto.Boolean.serialize(value.succeeded, stream)
            Proto.String.serialize(value.error, stream)
            Proto.String.serialize(value.name, stream)
        }

        override fun deserialize(stream: Source): GameTestResultsPacket {
            return GameTestResultsPacket(
                succeeded = Proto.Boolean.deserialize(stream),
                error = Proto.String.deserialize(stream),
                name = Proto.String.deserialize(stream),
            )
        }
    }
}
