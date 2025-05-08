package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeMountainsParamData(
    val steepBlock: Int,
    val northSlopes: Boolean,
    val southSlopes: Boolean,
    val westSlopes: Boolean,
    val eastSlopes: Boolean,
    val topSlideEnabled: Boolean,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeIntLE(steepBlock)
        byteBuf.writeBoolean(northSlopes)
        byteBuf.writeBoolean(southSlopes)
        byteBuf.writeBoolean(westSlopes)
        byteBuf.writeBoolean(eastSlopes)
        byteBuf.writeBoolean(topSlideEnabled)
    }
}
