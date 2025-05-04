package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

data class CreatePhotoPacket(
    val rawID: Long,
    val photoName: String,
    val photoItemName: String,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeLongLE(this.rawID)
        byteBuf.writeString(this.photoName)
        byteBuf.writeString(this.photoItemName)
    }

    override fun pid(): Int {
        return ProtocolInfo.CREATE_PHOTO_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
