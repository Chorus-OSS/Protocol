package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3

import org.chorus_oss.protocol.ProtocolInfo


class LecternUpdatePacket : DataPacket() {
    var page: Int = 0
    var totalPages: Int = 0
    lateinit var blockPosition: BlockVector3

    override fun pid(): Int {
        return ProtocolInfo.LECTERN_UPDATE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<LecternUpdatePacket> {
        override fun decode(byteBuf: ByteBuf): LecternUpdatePacket {
            val packet = LecternUpdatePacket()

            packet.page = byteBuf.readUnsignedByte().toInt()
            packet.totalPages = byteBuf.readUnsignedByte().toInt()
            packet.blockPosition = byteBuf.readBlockVector3()

            return packet
        }
    }
}
