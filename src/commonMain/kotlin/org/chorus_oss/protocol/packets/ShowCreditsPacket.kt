package org.chorus_oss.protocol.packets


class ShowCreditsPacket : Packet(id) {
    @JvmField
    var eid: Long = 0

    @JvmField
    var status: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(this.eid)
        byteBuf.writeVarInt(this.status)
    }

    override fun pid(): Int {
        return ProtocolInfo.SHOW_CREDITS_PACKET
    }



    companion object : PacketCodec<ShowCreditsPacket> {
        override fun deserialize(stream: Source): ShowCreditsPacket {
            val packet = ShowCreditsPacket()

            packet.eid = byteBuf.readActorRuntimeID()
            packet.status = byteBuf.readVarInt()

            return packet
        }

        const val STATUS_START_CREDITS: Int = 0
        const val STATUS_END_CREDITS: Int = 1
    }
}
