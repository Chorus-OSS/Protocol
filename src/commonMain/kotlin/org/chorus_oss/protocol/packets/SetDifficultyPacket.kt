package org.chorus_oss.protocol.packets


class SetDifficultyPacket : Packet(id) {
    var difficulty: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.difficulty)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_DIFFICULTY_PACKET
    }



    companion object : PacketCodec<SetDifficultyPacket> {
        override fun deserialize(stream: Source): SetDifficultyPacket {
            val packet = SetDifficultyPacket()

            packet.difficulty = byteBuf.readUnsignedVarInt()

            return packet
        }
    }
}
