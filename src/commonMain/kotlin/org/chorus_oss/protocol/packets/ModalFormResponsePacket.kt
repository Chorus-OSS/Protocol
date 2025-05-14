package org.chorus_oss.protocol.packets


class ModalFormResponsePacket : Packet(id) {
    var formId: Int = 0
    var data: String = "null"
    var cancelReason: Int = 0

    override fun pid(): Int {
        return ProtocolInfo.MODAL_FORM_RESPONSE_PACKET
    }



    companion object : PacketCodec<ModalFormResponsePacket> {
        override fun deserialize(stream: Source): ModalFormResponsePacket {
            val packet = ModalFormResponsePacket()
            packet.formId = byteBuf.readVarInt()
            if (Proto.Boolean.deserialize(stream)) {
                packet.data = Proto.String.deserialize(stream)
            }
            if (Proto.Boolean.deserialize(stream)) {
                packet.cancelReason = Proto.Byte.deserialize(stream).toInt()
            }
            return packet
        }
    }
}
