package org.chorus_oss.protocol.packets


class SetDefaultGameTypePacket : Packet(id) {
    var gamemode: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.gamemode)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_DEFAULT_GAME_TYPE_PACKET
    }



    companion object : PacketCodec<SetDefaultGameTypePacket> {
        override fun deserialize(stream: Source): SetDefaultGameTypePacket {
            val packet = SetDefaultGameTypePacket()

            packet.gamemode = byteBuf.readUnsignedVarInt()

            return packet
        }
    }
}
