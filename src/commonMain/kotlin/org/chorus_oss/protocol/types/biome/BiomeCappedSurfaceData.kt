package org.chorus_oss.protocol.types.biome

data class BiomeCappedSurfaceData(
    val floorBlocks: List<Int>,
    val ceilingBlocks: List<Int>,
    val seaBlock: UInt? = null,
    val foundationBlock: UInt? = null,
    val beachBlock: UInt? = null,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeArray(floorBlocks) { buf, data ->
            buf.writeIntLE(data)
        }
        byteBuf.writeArray(ceilingBlocks) { buf, data ->
            buf.writeIntLE(data)
        }
        byteBuf.writeNotNull(seaBlock) { byteBuf.writeIntLE(it.toInt()) }
        byteBuf.writeNotNull(foundationBlock) { byteBuf.writeIntLE(it.toInt()) }
        byteBuf.writeNotNull(beachBlock) { byteBuf.writeIntLE(it.toInt()) }
    }
}
