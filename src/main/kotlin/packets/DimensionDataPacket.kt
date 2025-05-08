package org.chorus_oss.protocol.packets


class DimensionDataPacket : DataPacket() {
    override fun encode(byteBuf: ByteBuf) {
        // TODO
    }

    override fun pid(): Int {
        return ProtocolInfo.DIMENSION_DATA_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
