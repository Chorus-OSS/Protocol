package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec

class ClientboundCloseFormPacket() : Packet(id) {
    companion object : PacketCodec<ClientboundCloseFormPacket> {
        override val id: Int = 310

        override fun deserialize(stream: Source): ClientboundCloseFormPacket = ClientboundCloseFormPacket()

        override fun serialize(value: ClientboundCloseFormPacket, stream: Sink) = Unit
    }
}
