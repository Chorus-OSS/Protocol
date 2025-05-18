package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String


data class ShowProfilePacket(
    val xuid: String,
) : Packet(id) {
    companion object : PacketCodec<ShowProfilePacket> {
        override val id: Int
            get() = ProtocolInfo.SHOW_PROFILE_PACKET

        override fun serialize(value: ShowProfilePacket, stream: Sink) {
            Proto.String.serialize(value.xuid, stream)
        }

        override fun deserialize(stream: Source): ShowProfilePacket {
            return ShowProfilePacket(
                xuid = Proto.String.deserialize(stream),
            )
        }
    }
}
