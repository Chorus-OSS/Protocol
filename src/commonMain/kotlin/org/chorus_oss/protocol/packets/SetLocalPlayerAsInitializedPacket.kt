package org.chorus_oss.protocol.packets


class SetLocalPlayerAsInitializedPacket : Packet(id) {
    var eid: Long = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarLong(eid)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_LOCAL_PLAYER_AS_INITIALIZED_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<SetLocalPlayerAsInitializedPacket> {
        override fun decode(byteBuf: ByteBuf): SetLocalPlayerAsInitializedPacket {
            val packet = SetLocalPlayerAsInitializedPacket()

            packet.eid = byteBuf.readUnsignedVarLong()

            return packet
        }
    }
}
