package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec


class ServerSettingsRequestPacket : Packet(id) {
    companion object : PacketCodec<ServerSettingsRequestPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.SERVER_SETTINGS_REQUEST_PACKET

        override fun serialize(
            value: ServerSettingsRequestPacket,
            stream: Sink
        ) = Unit

        override fun deserialize(stream: Source): ServerSettingsRequestPacket = ServerSettingsRequestPacket()
    }
}
