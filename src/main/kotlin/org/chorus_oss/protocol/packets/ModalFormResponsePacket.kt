package org.chorus_oss.protocol.packets


class ModalFormResponsePacket : DataPacket() {
    var formId: Int = 0
    var data: String = "null"
    var cancelReason: Int = 0

    override fun pid(): Int {
        return ProtocolInfo.MODAL_FORM_RESPONSE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ModalFormResponsePacket> {
        override fun decode(byteBuf: ByteBuf): ModalFormResponsePacket {
            val packet = ModalFormResponsePacket()
            packet.formId = byteBuf.readVarInt()
            if (byteBuf.readBoolean()) {
                packet.data = byteBuf.readString()
            }
            if (byteBuf.readBoolean()) {
                packet.cancelReason = byteBuf.readByte().toInt()
            }
            return packet
        }
    }
}
