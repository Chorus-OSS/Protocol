package org.chorus_oss.protocol.packets


class LevelChunkPacket : Packet(id) {
    @JvmField
    var chunkX: Int = 0

    @JvmField
    var chunkZ: Int = 0

    @JvmField
    var subChunkCount: Int = 0
    var cacheEnabled: Boolean = false
    var requestSubChunks: Boolean = false
    var subChunkLimit: Int = 0
    lateinit var blobIds: LongArray
    lateinit var data: ByteArray

    /**
     * @since v649
     */
    @JvmField
    var dimension: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.chunkX)
        byteBuf.writeVarInt(this.chunkZ)
        byteBuf.writeVarInt(this.dimension)
        if (this.requestSubChunks) {
            if (this.subChunkLimit < 0) {
                byteBuf.writeUnsignedVarInt(-1)
            } else {
                byteBuf.writeUnsignedVarInt(-2)
                byteBuf.writeUnsignedVarInt(this.subChunkLimit)
            }
        } else {
            byteBuf.writeUnsignedVarInt(this.subChunkCount)
        }
        byteBuf.writeBoolean(cacheEnabled)
        if (this.cacheEnabled) {
            byteBuf.writeUnsignedVarInt(blobIds.size)

            for (blobId in blobIds) {
                byteBuf.writeLongLE(blobId)
            }
        }
        byteBuf.writeByteArray(this.data)
    }

    override fun pid(): Int {
        return ProtocolInfo.LEVEL_CHUNK_PACKET
    }


}
