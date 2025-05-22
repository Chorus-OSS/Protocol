package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry

class ClientboundCloseFormPacket() : Packet(id) {
    companion object : PacketCodec<ClientboundCloseFormPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.CLIENTBOUND_CLOSE_FORM_PACKET

        override fun deserialize(stream: Source): ClientboundCloseFormPacket = ClientboundCloseFormPacket()

        override fun serialize(value: ClientboundCloseFormPacket, stream: Sink) = Unit
    }
}
