package org.chorus_oss.protocol.packets


class TransferPacket : Packet(id) {
    @JvmField
    var address: String? = null

    @JvmField
    var port: Int = 19132
    private var reloadWorld = false

    override fun decode(byteBuf: ByteBuf) {
        this.address = byteBuf.readString()
        this.port = byteBuf.readShortLE().toInt()
        this.reloadWorld = byteBuf.readBoolean()
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(address!!)
        byteBuf.writeShortLE(port)
        byteBuf.writeBoolean(this.reloadWorld)
    }

    override fun pid(): Int {
        return ProtocolInfo.TRANSFER_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
