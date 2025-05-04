package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.BlockChangeEntry


class UpdateSubChunkBlocksPacket : DataPacket() {
    var chunkX: Int = 0
    var chunkY: Int = 0
    var chunkZ: Int = 0

    val standardBlocks: MutableList<BlockChangeEntry> = mutableListOf()
    val extraBlocks: MutableList<BlockChangeEntry> = mutableListOf()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(chunkX)
        byteBuf.writeUnsignedVarInt(chunkY)
        byteBuf.writeVarInt(chunkZ)
        byteBuf.writeUnsignedVarInt(standardBlocks.size)
        for (each in standardBlocks) {
            byteBuf.writeBlockVector3(each.blockPos)
            byteBuf.writeUnsignedVarInt(each.runtimeID.toInt())
            byteBuf.writeUnsignedVarInt(each.updateFlags)
            byteBuf.writeUnsignedVarLong(each.messageEntityID)
            byteBuf.writeUnsignedVarInt(each.messageType.ordinal)
        }
        byteBuf.writeUnsignedVarInt(extraBlocks.size)
        for (each in extraBlocks) {
            byteBuf.writeBlockVector3(each.blockPos)
            byteBuf.writeUnsignedVarInt(each.runtimeID.toInt())
            byteBuf.writeUnsignedVarInt(each.updateFlags)
            byteBuf.writeUnsignedVarLong(each.messageEntityID)
            byteBuf.writeUnsignedVarInt(each.messageType.ordinal)
        }
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is UpdateSubChunkBlocksPacket) return false
        return chunkX == o.chunkX && chunkY == o.chunkY && chunkZ == o.chunkZ && standardBlocks == o.standardBlocks && extraBlocks == o.extraBlocks
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = 1
        result = result * PRIME + this.chunkX
        result = result * PRIME + this.chunkY
        result = result * PRIME + this.chunkZ
        result = result * PRIME + (standardBlocks as Any).hashCode()
        result = result * PRIME + (extraBlocks as Any).hashCode()
        return result
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_SUB_CHUNK_BLOCKS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
