package org.chorus_oss.protocol.packets


class SetPlayerGameTypePacket : Packet(id) {
    @JvmField
    var gamemode: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.gamemode)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_PLAYER_GAME_TYPE_PACKET
    }



    companion object : PacketCodec<SetPlayerGameTypePacket> {
        override fun deserialize(stream: Source): SetPlayerGameTypePacket {
            val packet = SetPlayerGameTypePacket()

            packet.gamemode = byteBuf.readVarInt()

            return packet
        }
    }
}
