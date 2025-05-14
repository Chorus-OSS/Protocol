package org.chorus_oss.protocol.packets


class RequestChunkRadiusPacket : Packet(id) {
    var radius: Int = 0

    private var maxRadius = 0

    override fun pid(): Int {
        return ProtocolInfo.REQUEST_CHUNK_RADIUS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<RequestChunkRadiusPacket> {
        override fun decode(byteBuf: ByteBuf): RequestChunkRadiusPacket {
            val packet = RequestChunkRadiusPacket()

            packet.radius = byteBuf.readVarInt()
            packet.maxRadius = byteBuf.readByte().toInt()

            return packet
        }
    }
}
