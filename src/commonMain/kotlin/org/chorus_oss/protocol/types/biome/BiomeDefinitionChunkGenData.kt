package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Boolean

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
    companion object : ProtoCodec<BiomeDefinitionChunkGenData> {
        override fun serialize(value: BiomeDefinitionChunkGenData, stream: Sink) {
            ProtoHelper.serializeNullable(value.climate, stream, BiomeClimateData)
            ProtoHelper.serializeNullable(value.consolidatedFeatures, stream, BiomeConsolidatedFeaturesData)
            ProtoHelper.serializeNullable(value.mountainParams, stream, BiomeMountainsParamData)
            ProtoHelper.serializeNullable(value.surfaceMaterialAdjustments, stream, BiomeSurfaceMaterialAdjustmentData)
            ProtoHelper.serializeNullable(value.surfaceMaterials, stream, BiomeSurfaceMaterialData)
            Proto.Boolean.serialize(value.hasSwampSurface, stream)
            Proto.Boolean.serialize(value.hasFrozenOceanSurface, stream)
            Proto.Boolean.serialize(value.hasTheEndSurface, stream)
            ProtoHelper.serializeNullable(value.mesaSurface, stream, BiomeMesaSurfaceData)
            ProtoHelper.serializeNullable(value.cappedSurface, stream, BiomeCappedSurfaceData)
            ProtoHelper.serializeNullable(value.overworldGenRules, stream, BiomeOverworldGenRulesData)
            ProtoHelper.serializeNullable(value.multinoiseGenRules, stream, BiomeMultinoiseGenRulesData)
            ProtoHelper.serializeNullable(value.legacyWorldGenRules, stream, BiomeLegacyWorldGenRulesData)
        }

        override fun deserialize(stream: Source): BiomeDefinitionChunkGenData {
            return BiomeDefinitionChunkGenData(
                climate = ProtoHelper.deserializeNullable(stream, BiomeClimateData),
                consolidatedFeatures = ProtoHelper.deserializeNullable(stream, BiomeConsolidatedFeaturesData),
                mountainParams = ProtoHelper.deserializeNullable(stream, BiomeMountainsParamData),
                surfaceMaterialAdjustments = ProtoHelper.deserializeNullable(
                    stream,
                    BiomeSurfaceMaterialAdjustmentData
                ),
                surfaceMaterials = ProtoHelper.deserializeNullable(stream, BiomeSurfaceMaterialData),
                hasSwampSurface = Proto.Boolean.deserialize(stream),
                hasFrozenOceanSurface = Proto.Boolean.deserialize(stream),
                hasTheEndSurface = Proto.Boolean.deserialize(stream),
                mesaSurface = ProtoHelper.deserializeNullable(stream, BiomeMesaSurfaceData),
                cappedSurface = ProtoHelper.deserializeNullable(stream, BiomeCappedSurfaceData),
                overworldGenRules = ProtoHelper.deserializeNullable(stream, BiomeOverworldGenRulesData),
                multinoiseGenRules = ProtoHelper.deserializeNullable(stream, BiomeMultinoiseGenRulesData),
                legacyWorldGenRules = ProtoHelper.deserializeNullable(stream, BiomeLegacyWorldGenRulesData),
            )
        }
    }
}
