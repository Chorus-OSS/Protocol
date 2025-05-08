package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf
import org.chorus_oss.protocol.types.biome.BiomeCappedSurfaceData
import org.chorus_oss.protocol.types.biome.BiomeClimateData

data class BiomeDefinitionChunkGenData(
    val climate: BiomeClimateData? = null,
    val consolidatedFeatures: BiomeConsolidatedFeaturesData? = null,
    val mountainParams: BiomeMountainsParamData? = null,
    val surfaceMaterialAdjustments: BiomeSurfaceMaterialAdjustmentData? = null,
    val surfaceMaterials: BiomeSurfaceMaterialData? = null,
    val hasSwampSurface: Boolean,
    val hasFrozenOceanSurface: Boolean,
    val hasTheEndSurface: Boolean,
    val mesaSurface: BiomeMesaSurfaceData? = null,
    val cappedSurface: BiomeCappedSurfaceData? = null,
    val overworldGenRules: BiomeOverworldGenRulesData? = null,
    val multinoiseGenRules: BiomeMultinoiseGenRulesData? = null,
    val legacyWorldGenRules: BiomeLegacyWorldGenRulesData? = null,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeNotNull(climate) { it.encode(byteBuf) }
        byteBuf.writeNotNull(consolidatedFeatures) { it.encode(byteBuf) }
        byteBuf.writeNotNull(mountainParams) { it.encode(byteBuf) }
        byteBuf.writeNotNull(surfaceMaterialAdjustments) { it.encode(byteBuf) }
        byteBuf.writeNotNull(surfaceMaterials) { it.encode(byteBuf) }
        byteBuf.writeBoolean(hasSwampSurface)
        byteBuf.writeBoolean(hasFrozenOceanSurface)
        byteBuf.writeBoolean(hasTheEndSurface)
        byteBuf.writeNotNull(mesaSurface) { it.encode(byteBuf) }
        byteBuf.writeNotNull(cappedSurface) { it.encode(byteBuf) }
        byteBuf.writeNotNull(overworldGenRules) { it.encode(byteBuf) }
        byteBuf.writeNotNull(multinoiseGenRules) { it.encode(byteBuf) }
        byteBuf.writeNotNull(legacyWorldGenRules) { it.encode(byteBuf) }
    }
}
