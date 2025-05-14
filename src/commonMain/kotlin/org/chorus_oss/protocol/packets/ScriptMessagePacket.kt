package org.chorus_oss.protocol.packets


class ScriptMessagePacket : Packet(id) {
    lateinit var channel: String
    lateinit var message: String

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(channel)
        byteBuf.writeString(message)
    }

    override fun pid(): Int {
        return ProtocolInfo.SCRIPT_MESSAGE_PACKET
    }



    companion object : PacketCodec<ScriptMessagePacket> {
        override fun deserialize(stream: Source): ScriptMessagePacket {
            val packet = ScriptMessagePacket()

            packet.channel = Proto.String.deserialize(stream)
            packet.message = Proto.String.deserialize(stream)

            return packet
        }
    }
}
