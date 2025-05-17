package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int

data class LoginPacket(
    val clientProtocol: Int,
    val connectionRequest: List<Byte>
) : Packet(id) {
    companion object : PacketCodec<LoginPacket> {
        override val id: Int
            get() = ProtocolInfo.LOGIN_PACKET

        override fun serialize(value: LoginPacket, stream: Sink) {
            ProtoBE.Int.serialize(value.clientProtocol, stream)
            ProtoHelper.serializeList(value.connectionRequest, stream, Proto.Byte)
        }

        override fun deserialize(stream: Source): LoginPacket {
            return LoginPacket(
                clientProtocol = ProtoBE.Int.deserialize(stream),
                connectionRequest = ProtoHelper.deserializeList(stream, Proto.Byte)
            )
        }
    }
}
