package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

data class AwardAchievementPacket(
    val achievementID: Int,
) : Packet(id) {
    companion object : PacketCodec<AwardAchievementPacket> {
        override val id: Int = 309

        override fun serialize(value: AwardAchievementPacket, stream: Sink) {
            ProtoLE.Int.serialize(value.achievementID, stream)
        }

        override fun deserialize(stream: Source): AwardAchievementPacket {
            return AwardAchievementPacket(
                achievementID = ProtoLE.Int.deserialize(stream),
            )
        }
    }
}
