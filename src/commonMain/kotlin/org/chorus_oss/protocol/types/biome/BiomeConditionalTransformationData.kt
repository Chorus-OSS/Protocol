package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeConditionalTransformationData(
    val weightedBiome: List<BiomeWeightedData>,
    val conditionJSON: Short,
    val minPassingNeighbors: UInt,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeArray(weightedBiome) { buf, data ->
            data.encode(buf)
        }
        byteBuf.writeShortLE(conditionJSON.toInt())
        byteBuf.writeIntLE(minPassingNeighbors.toInt())
    }
}
