package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry


class ClientToServerHandshakePacket : Packet(id) {
    companion object : PacketCodec<ClientToServerHandshakePacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.CLIENT_TO_SERVER_HANDSHAKE_PACKET

        override fun serialize(value: ClientToServerHandshakePacket, stream: Sink) = Unit

        override fun deserialize(stream: Source): ClientToServerHandshakePacket = ClientToServerHandshakePacket()
    }
}
