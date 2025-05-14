package org.chorus_oss.protocol.packets


class GUIDataPickItemPacket : Packet(id) {
    var hotbarSlot: Int = 0

    override fun decode(byteBuf: ByteBuf) {
        this.hotbarSlot = byteBuf.readIntLE()
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeIntLE(this.hotbarSlot)
    }

    override fun pid(): Int {
        return ProtocolInfo.GUI_DATA_PICK_ITEM_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
