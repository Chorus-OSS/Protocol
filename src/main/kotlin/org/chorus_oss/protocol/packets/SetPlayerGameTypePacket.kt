package org.chorus_oss.protocol.packets


class SetPlayerGameTypePacket : DataPacket() {
    @JvmField
    var gamemode: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.gamemode)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_PLAYER_GAME_TYPE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<SetPlayerGameTypePacket> {
        override fun decode(byteBuf: ByteBuf): SetPlayerGameTypePacket {
            val packet = SetPlayerGameTypePacket()

            packet.gamemode = byteBuf.readVarInt()

            return packet
        }
    }
}
