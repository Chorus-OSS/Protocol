package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class RiderJumpPacket : DataPacket() {
    /**
     * Corresponds to jump progress bars 0-100
     */
    var jumpStrength: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.jumpStrength)
    }

    override fun pid(): Int {
        return ProtocolInfo.RIDER_JUMP_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<RiderJumpPacket> {
        override fun decode(byteBuf: ByteBuf): RiderJumpPacket {
            val packet = RiderJumpPacket()

            packet.jumpStrength = byteBuf.readVarInt()

            return packet
        }
    }
}
