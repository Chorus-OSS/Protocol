package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoBE
import org.chorus_oss.protocol.core.types.Int


data class RequestNetworkSettingsPacket(
    val clientProtocol: Int,
) : Packet(id) {
    companion object : PacketCodec<RequestNetworkSettingsPacket> {
        override val id: Int
            get() = ProtocolInfo.REQUEST_NETWORK_SETTINGS_PACKET

        override fun serialize(
            value: RequestNetworkSettingsPacket,
            stream: Sink
        ) {
            ProtoBE.Int.serialize(value.clientProtocol, stream)
        }

        override fun deserialize(stream: Source): RequestNetworkSettingsPacket {
            return RequestNetworkSettingsPacket(
                clientProtocol = ProtoBE.Int.deserialize(stream),
            )
        }
    }
}
