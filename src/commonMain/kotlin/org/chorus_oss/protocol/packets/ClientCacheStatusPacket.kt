package org.chorus_oss.protocol.packets


data class ClientCacheStatusPacket(
    val isCacheSupported: Boolean
) : Packet(id) {
    override fun pid(): Int {
        return ProtocolInfo.CLIENT_CACHE_STATUS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ClientCacheStatusPacket> {
        override fun decode(byteBuf: ByteBuf): ClientCacheStatusPacket {
            return ClientCacheStatusPacket(
                isCacheSupported = byteBuf.readBoolean()
            )
        }
    }
}
