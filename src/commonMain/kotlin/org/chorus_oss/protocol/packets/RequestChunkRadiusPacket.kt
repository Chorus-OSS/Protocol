package org.chorus_oss.protocol.packets


class RequestChunkRadiusPacket : Packet(id) {
    var radius: Int = 0

    private var maxRadius = 0

    override fun pid(): Int {
        return ProtocolInfo.REQUEST_CHUNK_RADIUS_PACKET
    }



    companion object : PacketCodec<RequestChunkRadiusPacket> {
        override fun deserialize(stream: Source): RequestChunkRadiusPacket {
            val packet = RequestChunkRadiusPacket()

            packet.radius = byteBuf.readVarInt()
            packet.maxRadius = Proto.Byte.deserialize(stream).toInt()

            return packet
        }
    }
}
