package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeMultinoiseGenRulesData(
    val temperature: Float,
    val humidity: Float,
    val altitude: Float,
    val weirdness: Float,
    val weight: Float,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeFloatLE(temperature)
        byteBuf.writeFloatLE(humidity)
        byteBuf.writeFloatLE(altitude)
        byteBuf.writeFloatLE(weirdness)
        byteBuf.writeFloatLE(weight)
    }
}
