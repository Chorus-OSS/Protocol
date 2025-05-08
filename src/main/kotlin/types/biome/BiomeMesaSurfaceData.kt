package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeMesaSurfaceData(
    val clayMaterial: UInt,
    val hardClayMaterial: UInt,
    val brycePillars: Boolean,
    val hasForest: Boolean,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeIntLE(clayMaterial.toInt())
        byteBuf.writeIntLE(hardClayMaterial.toInt())
        byteBuf.writeBoolean(brycePillars)
        byteBuf.writeBoolean(hasForest)
    }
}
