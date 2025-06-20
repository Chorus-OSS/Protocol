package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ControlScheme

data class ClientboundControlSchemeSetPacket(
    val controlScheme: ControlScheme
) : Packet(id) {
    companion object : PacketCodec<ClientboundControlSchemeSetPacket> {
        override val id: Int = 327

        override fun deserialize(stream: Source): ClientboundControlSchemeSetPacket {
            return ClientboundControlSchemeSetPacket(
                controlScheme = ControlScheme.deserialize(stream)
            )
        }

        override fun serialize(value: ClientboundControlSchemeSetPacket, stream: Sink) {
            ControlScheme.serialize(value.controlScheme, stream)
        }
    }
}