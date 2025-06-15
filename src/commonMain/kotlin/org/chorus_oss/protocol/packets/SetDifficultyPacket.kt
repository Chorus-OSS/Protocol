package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt


data class SetDifficultyPacket(
    val difficulty: UInt
) : Packet(id) {
    companion object : PacketCodec<SetDifficultyPacket> {
        override val id: Int = 60

        override fun serialize(value: SetDifficultyPacket, stream: Sink) {
            ProtoVAR.UInt.serialize(value.difficulty, stream)
        }

        override fun deserialize(stream: Source): SetDifficultyPacket {
            return SetDifficultyPacket(
                difficulty = ProtoVAR.UInt.deserialize(stream),
            )
        }
    }
}
