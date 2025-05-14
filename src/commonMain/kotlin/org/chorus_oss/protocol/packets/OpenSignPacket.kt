package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3

class OpenSignPacket : Packet(id) {
    @JvmField
    var position: BlockVector3? = null

    @JvmField
    var frontSide: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBlockVector3(position!!)
        byteBuf.writeBoolean(frontSide)
    }

    override fun pid(): Int {
        return ProtocolInfo.OPEN_SIGN
    }



    companion object : PacketCodec<OpenSignPacket> {
        override fun deserialize(stream: Source): OpenSignPacket {
            val packet = OpenSignPacket()

            packet.position = byteBuf.readBlockVector3()
            packet.frontSide = Proto.Boolean.deserialize(stream)

            return packet
        }
    }
}
