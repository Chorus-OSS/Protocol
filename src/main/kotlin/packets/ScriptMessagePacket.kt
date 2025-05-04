package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class ScriptMessagePacket : DataPacket() {
    lateinit var channel: String
    lateinit var message: String

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(channel)
        byteBuf.writeString(message)
    }

    override fun pid(): Int {
        return ProtocolInfo.SCRIPT_MESSAGE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ScriptMessagePacket> {
        override fun decode(byteBuf: ByteBuf): ScriptMessagePacket {
            val packet = ScriptMessagePacket()

            packet.channel = byteBuf.readString()
            packet.message = byteBuf.readString()

            return packet
        }
    }
}
