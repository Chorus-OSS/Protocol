package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoBE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String

data class LoginPacket(
    val clientProtocol: Int,
    val connectionRequest: String,
) : Packet(id) {
    companion object : PacketCodec<LoginPacket> {
        override val id: Int = 1

        override fun serialize(value: LoginPacket, stream: Sink) {
            ProtoBE.Int.serialize(value.clientProtocol, stream)
            Proto.String.serialize(value.connectionRequest, stream)
        }

        override fun deserialize(stream: Source): LoginPacket {
            return LoginPacket(
                clientProtocol = ProtoBE.Int.deserialize(stream),
                connectionRequest = Proto.String.deserialize(stream),
            )
        }
    }
}
