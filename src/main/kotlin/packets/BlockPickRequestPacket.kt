package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3

import org.chorus_oss.protocol.ProtocolInfo

data class BlockPickRequestPacket(
    val position: BlockVector3,
    var withData: Boolean,
    var maxSlots: Int,
) : DataPacket() {
    override fun pid(): Int {
        return ProtocolInfo.BLOCK_PICK_REQUEST_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<BlockPickRequestPacket> {
        override fun decode(byteBuf: ByteBuf): BlockPickRequestPacket {
            return BlockPickRequestPacket(
                position = byteBuf.readSignedBlockPosition(),
                withData = byteBuf.readBoolean(),
                maxSlots = byteBuf.readByte().toInt()
            )
        }
    }
}
