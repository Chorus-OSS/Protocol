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

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<OpenSignPacket> {
        override fun decode(byteBuf: ByteBuf): OpenSignPacket {
            val packet = OpenSignPacket()

            packet.position = byteBuf.readBlockVector3()
            packet.frontSide = byteBuf.readBoolean()

            return packet
        }
    }
}
