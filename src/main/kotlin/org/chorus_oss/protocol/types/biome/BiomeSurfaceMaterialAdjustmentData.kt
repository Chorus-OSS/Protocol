package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeSurfaceMaterialAdjustmentData(
    val adjustment: List<BiomeElementData>
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeArray(adjustment) { buf, data ->
            data.encode(buf)
        }
    }
}
