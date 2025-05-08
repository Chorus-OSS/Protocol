package org.chorus_oss.protocol.packets


class PositionTrackingDBClientRequestPacket : DataPacket() {
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

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<PositionTrackingDBClientRequestPacket> {
        override fun decode(byteBuf: ByteBuf): PositionTrackingDBClientRequestPacket {
            val packet = PositionTrackingDBClientRequestPacket()

            packet.action = ACTIONS[byteBuf.readByte().toInt()]
            packet.trackingId = byteBuf.readVarInt()

            return packet
        }

        private val ACTIONS = Action.entries.toTypedArray()
    }
}
