package org.chorus_oss.protocol.packets

import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec

class ClientboundCloseFormPacket() {
    companion object : PacketCodec<ClientboundCloseFormPacket> {
        override val id: Int
            get() = ProtocolInfo.CLIENTBOUND_CLOSE_FORM_PACKET

        override fun deserialize(stream: Buffer): ClientboundCloseFormPacket = ClientboundCloseFormPacket()

        override fun serialize(value: ClientboundCloseFormPacket, stream: Buffer) = Unit
    }
}
