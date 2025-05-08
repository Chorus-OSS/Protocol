package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeWeightedData(
    val biome: Short,
    val weight: UInt,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeShortLE(biome.toInt())
        byteBuf.writeIntLE(weight.toInt())
    }
}
