package org.chorus_oss.protocol.packets


class PositionTrackingDBClientRequestPacket : Packet(id) {
    var action: Action? = null
    var trackingId: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(action!!.ordinal.toByte().toInt())
        byteBuf.writeVarInt(trackingId)
    }

    override fun pid(): Int {
        return ProtocolInfo.POS_TRACKING_CLIENT_REQUEST_PACKET
    }

    enum class Action {
        QUERY
    }



    companion object : PacketCodec<PositionTrackingDBClientRequestPacket> {
        override fun deserialize(stream: Source): PositionTrackingDBClientRequestPacket {
            val packet = PositionTrackingDBClientRequestPacket()

            packet.action = ACTIONS[Proto.Byte.deserialize(stream).toInt()]
            packet.trackingId = byteBuf.readVarInt()

            return packet
        }

        private val ACTIONS = Action.entries.toTypedArray()
    }
}
