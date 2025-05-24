package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String

data class LoginPacket(
    val clientProtocol: Int,
    val connectionRequest: String,
) : Packet(id) {
    companion object : PacketCodec<LoginPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.LOGIN_PACKET

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
