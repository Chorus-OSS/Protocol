package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float

data class ServerStatsPacket(
    val serverTime: Float,
    val networkTime: Float,
) : Packet(id) {
    companion object : PacketCodec<ServerStatsPacket> {
        override val id: Int = 192

        override fun serialize(value: ServerStatsPacket, stream: Sink) {
            ProtoLE.Float.serialize(value.serverTime, stream)
            ProtoLE.Float.serialize(value.networkTime, stream)
        }

        override fun deserialize(stream: Source): ServerStatsPacket {
            return ServerStatsPacket(
                serverTime = ProtoLE.Float.deserialize(stream),
                networkTime = ProtoLE.Float.deserialize(stream),
            )
        }
    }
}
