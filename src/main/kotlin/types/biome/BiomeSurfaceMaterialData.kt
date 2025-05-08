package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeSurfaceMaterialData(
    val topBlock: Int,
    val midBlock: Int,
    val seaFloorBlock: Int,
    val foundationBlock: Int,
    val seaBlock: Int,
    val seaFloorDepth: Int,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeIntLE(topBlock)
        byteBuf.writeIntLE(midBlock)
        byteBuf.writeIntLE(seaFloorBlock)
        byteBuf.writeIntLE(seaBlock)
        byteBuf.writeIntLE(seaFloorDepth)
        byteBuf.writeIntLE(foundationBlock)
    }
}
