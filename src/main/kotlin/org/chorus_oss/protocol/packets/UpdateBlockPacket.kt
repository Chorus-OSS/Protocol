package org.chorus_oss.protocol.packets


open class UpdateBlockPacket : DataPacket() {
    @JvmField
    var x: Int = 0

    @JvmField
    var z: Int = 0

    @JvmField
    var y: Int = 0

    @JvmField
    var blockRuntimeId: Int = 0

    @JvmField
    var flags: Int = 0
    var dataLayer: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBlockVector3(x, y, z)
        byteBuf.writeUnsignedVarInt(blockRuntimeId)
        byteBuf.writeUnsignedVarInt(flags)
        byteBuf.writeUnsignedVarInt(dataLayer)
    }

    class Entry(
        val x: Int,
        val z: Int,
        val y: Int, val blockId: String, val blockData: Int,
        val flags: Int
    )

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_BLOCK_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object {
        const val FLAG_NONE: Int = 0
        const val FLAG_NEIGHBORS: Int = 1
        const val FLAG_NETWORK: Int = 2
        const val FLAG_NOGRAPHIC: Int = 4
        const val FLAG_PRIORITY: Int = 8
        const val FLAG_ALL: Int = (FLAG_NEIGHBORS or FLAG_NETWORK)
        const val FLAG_ALL_PRIORITY: Int = (FLAG_ALL or FLAG_PRIORITY)
    }
}
