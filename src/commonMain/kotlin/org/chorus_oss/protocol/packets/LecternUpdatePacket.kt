package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3


class LecternUpdatePacket : Packet(id) {
    var page: Int = 0
    var totalPages: Int = 0
    lateinit var blockPosition: BlockVector3

    override fun pid(): Int {
        return ProtocolInfo.LECTERN_UPDATE_PACKET
    }



    companion object : PacketCodec<LecternUpdatePacket> {
        override fun deserialize(stream: Source): LecternUpdatePacket {
            val packet = LecternUpdatePacket()

            packet.page = byteBuf.readUnsignedByte().toInt()
            packet.totalPages = byteBuf.readUnsignedByte().toInt()
            packet.blockPosition = byteBuf.readBlockVector3()

            return packet
        }
    }
}
