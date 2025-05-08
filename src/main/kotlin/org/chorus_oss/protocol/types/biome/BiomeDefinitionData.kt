package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeDefinitionData(
    val id: UShort? = null,
    val temperature: Float,
    val downfall: Float,
    val redSporeDensity: Float,
    val blueSporeDensity: Float,
    val ashDensity: Float,
    val whiteAshDensity: Float,
    val depth: Float,
    val scale: Float,
    val mapWaterColorARGB: Int,
    val rain: Boolean,
    val tags: BiomeTagsData? = null,
    val chunkGenData: BiomeDefinitionChunkGenData? = null
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeNotNull(id) { byteBuf.writeShortLE(it.toInt()) }
        byteBuf.writeFloatLE(temperature)
        byteBuf.writeFloatLE(downfall)
        byteBuf.writeFloatLE(redSporeDensity)
        byteBuf.writeFloatLE(blueSporeDensity)
        byteBuf.writeFloatLE(ashDensity)
        byteBuf.writeFloatLE(whiteAshDensity)
        byteBuf.writeFloatLE(depth)
        byteBuf.writeFloatLE(scale)
        byteBuf.writeIntLE(mapWaterColorARGB)
        byteBuf.writeBoolean(rain)
        byteBuf.writeNotNull(tags) { it.encode(byteBuf) }
        byteBuf.writeNotNull(chunkGenData) { it.encode(byteBuf) }
    }
}
