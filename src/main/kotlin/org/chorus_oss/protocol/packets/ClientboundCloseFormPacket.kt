package org.chorus_oss.protocol.packets

class ClientboundCloseFormPacket : DataPacket() {
    override fun pid(): Int {
        return ProtocolInfo.CLIENTBOUND_CLOSE_FORM_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
