package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class SetDefaultGameTypePacket : DataPacket() {
    var gamemode: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.gamemode)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_DEFAULT_GAME_TYPE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<SetDefaultGameTypePacket> {
        override fun decode(byteBuf: ByteBuf): SetDefaultGameTypePacket {
            val packet = SetDefaultGameTypePacket()

            packet.gamemode = byteBuf.readUnsignedVarInt()

            return packet
        }
    }
}
