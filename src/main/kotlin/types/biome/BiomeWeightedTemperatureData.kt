package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeWeightedTemperatureData(
    val temperature: BiomeTemperatureCategory,
    val weight: UInt,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeVarInt(temperature.ordinal)
        byteBuf.writeIntLE(weight.toInt())
    }
}
