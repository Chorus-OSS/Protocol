package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeConsolidatedFeaturesData(
    val features: List<BiomeConsolidatedFeatureData>
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeArray(features) { buf, data ->
            data.encode(buf)
        }
    }
}
