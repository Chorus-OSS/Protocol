package org.chorus_oss.protocol.packets


data class ChunkRadiusUpdatedPacket(
    val radius: Int
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.radius)
    }

    override fun pid(): Int {
        return ProtocolInfo.CHUNK_RADIUS_UPDATED_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
