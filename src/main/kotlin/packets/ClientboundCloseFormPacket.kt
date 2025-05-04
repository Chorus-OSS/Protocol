package org.chorus_oss.protocol.packets

import org.chorus_oss.protocol.ProtocolInfo

class ClientboundCloseFormPacket : DataPacket() {
    override fun pid(): Int {
        return ProtocolInfo.CLIENTBOUND_CLOSE_FORM_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
