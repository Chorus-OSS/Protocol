package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.types.ControlScheme

data class ClientboundControlSchemeSetPacket(
    val controlScheme: ControlScheme
) : Packet(id) {
    companion object : PacketCodec<ClientboundControlSchemeSetPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.CLIENTBOUND_CONTROL_SCHEME_SET_PACKET

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