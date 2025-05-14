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



    companion object : PacketCodec<ServerboundLoadingScreenPacket> {
        override fun deserialize(stream: Source): ServerboundLoadingScreenPacket {
            val packet = ServerboundLoadingScreenPacket()

            packet.type = ServerboundLoadingScreenPacketType.entries[byteBuf.readVarInt()]
            if (Proto.Boolean.deserialize(stream)) {
                packet.loadingScreenId = byteBuf.readIntLE()
            }

            return packet
        }
    }
}
