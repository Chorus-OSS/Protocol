package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec


class ServerSettingsRequestPacket : Packet(id) {
    companion object : PacketCodec<ServerSettingsRequestPacket> {
        override val id: Int = 102

        override fun serialize(
            value: ServerSettingsRequestPacket,
            stream: Sink
        ) = Unit

        override fun deserialize(stream: Source): ServerSettingsRequestPacket = ServerSettingsRequestPacket()
    }
}
