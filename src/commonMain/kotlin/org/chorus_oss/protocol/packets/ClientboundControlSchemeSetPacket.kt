package org.chorus_oss.chorus.network.protocol

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf
import org.chorus_oss.protocol.packets.PacketHandler
import org.chorus_oss.protocol.types.ControlScheme

data class ClientboundControlSchemeSetPacket(
    val controlScheme: ControlScheme
) : DataPacket(), PacketEncoder {
    override fun pid(): Int {
        return ProtocolInfo.CLIENTBOUND_CONTROL_SCHEME_SET_PACKET
    }

    override fun handle(handler: PacketHandler) {}

    override fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeByte(controlScheme.ordinal)
    }
}