package org.chorus_oss.protocol.packets


class ModalFormRequestPacket : DataPacket() {
    @JvmField
    var formId: Int = 0

    lateinit var data: String

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.formId)
        byteBuf.writeString(this.data)
    }

    override fun pid(): Int {
        return ProtocolInfo.MODAL_FORM_REQUEST_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
