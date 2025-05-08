package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.BlockSyncType


class UpdateBlockSyncedPacket : UpdateBlockPacket() {
    var actorUniqueId: Long = 0
    var updateType: BlockSyncType? = null

    override fun encode(byteBuf: ByteBuf) {
        super.encode(byteBuf)
        byteBuf.writeUnsignedVarLong(actorUniqueId)
        byteBuf.writeUnsignedVarLong(updateType!!.ordinal.toLong())
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_BLOCK_SYNCED_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
