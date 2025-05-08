package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeElementData(
    val noiseFrequencyScale: Float,
    val noiseLowerBound: Float,
    val noiseUpperBound: Float,
    val heightMinType: ExpressionOp,
    val heightMin: Short,
    val heightMaxType: ExpressionOp,
    val heightMax: Short,
    val adjustedMaterials: BiomeSurfaceMaterialData,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeFloatLE(noiseFrequencyScale)
        byteBuf.writeFloatLE(noiseLowerBound)
        byteBuf.writeFloatLE(noiseUpperBound)
        byteBuf.writeVarInt(heightMinType.ordinal)
        byteBuf.writeShortLE(heightMin.toInt())
        byteBuf.writeVarInt(heightMaxType.ordinal)
        byteBuf.writeShortLE(heightMax.toInt())
        adjustedMaterials.encode(byteBuf)
    }
}
