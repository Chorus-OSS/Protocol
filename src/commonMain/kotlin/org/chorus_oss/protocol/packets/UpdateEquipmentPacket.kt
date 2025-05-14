package org.chorus_oss.protocol.packets


class UpdateEquipmentPacket : Packet(id) {
    var windowId: Int = 0
    var windowType: Int = 0
    var size: Int = 0
    var eid: Long = 0
    var namedtag: ByteArray = byteArrayOf()

    override fun decode(byteBuf: ByteBuf) {
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(windowId.toByte().toInt())
        byteBuf.writeByte(windowType.toByte().toInt())
        byteBuf.writeVarInt(size)
        byteBuf.writeActorUniqueID(this.eid)
        byteBuf.writeBytes(this.namedtag)
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_EQUIPMENT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
