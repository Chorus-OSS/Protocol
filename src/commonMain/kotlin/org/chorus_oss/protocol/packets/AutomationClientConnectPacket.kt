package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String


data class AutomationClientConnectPacket(
    val serverURI: String
) : Packet(id) {
    companion object : PacketCodec<AutomationClientConnectPacket> {
        override val id: Int = 95

        override fun serialize(
            value: AutomationClientConnectPacket,
            stream: Sink
        ) {
            Proto.String.serialize(value.serverURI, stream)
        }

        override fun deserialize(stream: Source): AutomationClientConnectPacket {
            return AutomationClientConnectPacket(
                serverURI = Proto.String.deserialize(stream)
            )
        }
    }
}
