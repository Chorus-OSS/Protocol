package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String


data class ServerToClientHandshakePacket(
    val jwt: String,
) : Packet(id) {
    companion object : PacketCodec<ServerToClientHandshakePacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.SERVER_TO_CLIENT_HANDSHAKE_PACKET

        override fun serialize(
            value: ServerToClientHandshakePacket,
            stream: Sink
        ) {
            Proto.String.serialize(value.jwt, stream)
        }

        override fun deserialize(stream: Source): ServerToClientHandshakePacket {
            return ServerToClientHandshakePacket(
                jwt = Proto.String.deserialize(stream),
            )
        }
    }
}
