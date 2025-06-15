package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int


data class SetDefaultGameTypePacket(
    val gameType: Int
) : Packet(id) {
    companion object : PacketCodec<SetDefaultGameTypePacket> {
        override val id: Int = 105

        override fun serialize(
            value: SetDefaultGameTypePacket,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.gameType, stream)
        }

        override fun deserialize(stream: Source): SetDefaultGameTypePacket {
            return SetDefaultGameTypePacket(
                gameType = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
