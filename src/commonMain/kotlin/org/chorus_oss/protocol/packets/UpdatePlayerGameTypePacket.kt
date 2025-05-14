package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.GameType


class UpdatePlayerGameTypePacket : Packet(id) {
    @JvmField
    var gameType: GameType? = null

    @JvmField
    var entityId: Long = 0
    var tick: Long = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(gameType!!.ordinal)
        byteBuf.writeVarLong(entityId)
        byteBuf.writeUnsignedVarLong(tick)
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_PLAYER_GAME_TYPE_PACKET
    }



    companion object : PacketCodec<UpdatePlayerGameTypePacket> {
        override fun deserialize(stream: Source): UpdatePlayerGameTypePacket {
            val packet = UpdatePlayerGameTypePacket()

            packet.gameType = GameType.from(byteBuf.readVarInt())
            packet.entityId = byteBuf.readVarLong()
            packet.tick = byteBuf.readUnsignedVarLong()

            return packet
        }
    }
}
