package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeConsolidatedFeatureData(
    val scatter: BiomeScatterParamData,
    val feature: Short,
    val identifier: Short,
    val pass: Short,
    val canUseInternal: Boolean,
) {
    fun encode(byteBuf: HandleByteBuf) {
        scatter.encode(byteBuf)
        byteBuf.writeShortLE(feature.toInt())
        byteBuf.writeShortLE(identifier.toInt())
        byteBuf.writeShortLE(pass.toInt())
        byteBuf.writeBoolean(canUseInternal)
    }
}
