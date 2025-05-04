package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3

import org.chorus_oss.protocol.ProtocolInfo

data class BlockEventPacket(
    val blockPosition: BlockVector3,
    val eventType: Int,
    val eventValue: Int,
) : DataPacket() {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBlockVector3(this.blockPosition)
        byteBuf.writeVarInt(this.eventType)
        byteBuf.writeVarInt(this.eventValue)
    }

    override fun pid(): Int {
        return ProtocolInfo.BLOCK_EVENT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
