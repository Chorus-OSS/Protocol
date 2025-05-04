package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

class SetDifficultyPacket : DataPacket() {
    var difficulty: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.difficulty)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_DIFFICULTY_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<SetDifficultyPacket> {
        override fun decode(byteBuf: ByteBuf): SetDifficultyPacket {
            val packet = SetDifficultyPacket()

            packet.difficulty = byteBuf.readUnsignedVarInt()

            return packet
        }
    }
}
