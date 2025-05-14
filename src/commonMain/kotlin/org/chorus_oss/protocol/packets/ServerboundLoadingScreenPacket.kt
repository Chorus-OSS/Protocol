package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.ServerboundLoadingScreenPacketType


class ServerboundLoadingScreenPacket : Packet(id) {
    private var type: ServerboundLoadingScreenPacketType? = null

    /**
     * Optional int, not present if null
     */
    private var loadingScreenId: Int? = null

    override fun pid(): Int {
        return ProtocolInfo.SERVERBOUND_LOADING_SCREEN_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ServerboundLoadingScreenPacket> {
        override fun decode(byteBuf: ByteBuf): ServerboundLoadingScreenPacket {
            val packet = ServerboundLoadingScreenPacket()

            packet.type = ServerboundLoadingScreenPacketType.entries[byteBuf.readVarInt()]
            if (byteBuf.readBoolean()) {
                packet.loadingScreenId = byteBuf.readIntLE()
            }

            return packet
        }
    }
}
