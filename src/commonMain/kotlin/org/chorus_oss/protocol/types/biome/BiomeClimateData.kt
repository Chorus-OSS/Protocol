package org.chorus_oss.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeClimateData(
    val temperature: Float,
    val downfall: Float,
    val redSporeDensity: Float,
    val blueSporeDensity: Float,
    val ashDensity: Float,
    val whiteAshDensity: Float,
    val snowAccumulationMin: Float,
    val snowAccumulationMax: Float
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeFloatLE(temperature)
        byteBuf.writeFloatLE(downfall)
        byteBuf.writeFloatLE(redSporeDensity)
        byteBuf.writeFloatLE(blueSporeDensity)
        byteBuf.writeFloatLE(ashDensity)
        byteBuf.writeFloatLE(whiteAshDensity)
        byteBuf.writeFloatLE(snowAccumulationMin)
        byteBuf.writeFloatLE(snowAccumulationMax)
    }
}
