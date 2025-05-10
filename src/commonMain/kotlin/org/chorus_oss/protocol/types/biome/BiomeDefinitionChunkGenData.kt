package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
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
        override fun serialize(value: BiomeDefinitionChunkGenData, stream: Buffer) {
            ProtoHelper.serializeNullable(value.climate, stream, BiomeClimateData::serialize)
            ProtoHelper.serializeNullable(value.consolidatedFeatures, stream, BiomeConsolidatedFeaturesData::serialize)
            ProtoHelper.serializeNullable(value.mountainParams, stream, BiomeMountainsParamData::serialize)
            ProtoHelper.serializeNullable(value.surfaceMaterialAdjustments, stream, BiomeSurfaceMaterialAdjustmentData::serialize)
            ProtoHelper.serializeNullable(value.surfaceMaterials, stream, BiomeSurfaceMaterialData::serialize)
            Proto.Boolean.serialize(value.hasSwampSurface, stream)
            Proto.Boolean.serialize(value.hasFrozenOceanSurface, stream)
            Proto.Boolean.serialize(value.hasTheEndSurface, stream)
            ProtoHelper.serializeNullable(value.mesaSurface, stream, BiomeMesaSurfaceData::serialize)
            ProtoHelper.serializeNullable(value.cappedSurface, stream, BiomeCappedSurfaceData::serialize)
            ProtoHelper.serializeNullable(value.overworldGenRules, stream, BiomeOverworldGenRulesData::serialize)
            ProtoHelper.serializeNullable(value.multinoiseGenRules, stream, BiomeMultinoiseGenRulesData::serialize)
            ProtoHelper.serializeNullable(value.legacyWorldGenRules, stream, BiomeLegacyWorldGenRulesData::serialize)
        }

        override fun deserialize(stream: Buffer): BiomeDefinitionChunkGenData {
            return BiomeDefinitionChunkGenData(
                climate = ProtoHelper.deserializeNullable(stream, BiomeClimateData::deserialize),
                consolidatedFeatures = ProtoHelper.deserializeNullable(stream, BiomeConsolidatedFeaturesData::deserialize),
                mountainParams = ProtoHelper.deserializeNullable(stream, BiomeMountainsParamData::deserialize),
                surfaceMaterialAdjustments = ProtoHelper.deserializeNullable(stream, BiomeSurfaceMaterialAdjustmentData::deserialize),
                surfaceMaterials = ProtoHelper.deserializeNullable(stream, BiomeSurfaceMaterialData::deserialize),
                hasSwampSurface = Proto.Boolean.deserialize(stream),
                hasFrozenOceanSurface = Proto.Boolean.deserialize(stream),
                hasTheEndSurface = Proto.Boolean.deserialize(stream),
                mesaSurface = ProtoHelper.deserializeNullable(stream, BiomeMesaSurfaceData::deserialize),
                cappedSurface = ProtoHelper.deserializeNullable(stream, BiomeCappedSurfaceData::deserialize),
                overworldGenRules = ProtoHelper.deserializeNullable(stream, BiomeOverworldGenRulesData::deserialize),
                multinoiseGenRules = ProtoHelper.deserializeNullable(stream, BiomeMultinoiseGenRulesData::deserialize),
                legacyWorldGenRules = ProtoHelper.deserializeNullable(stream, BiomeLegacyWorldGenRulesData::deserialize),
            )
        }
    }
}
