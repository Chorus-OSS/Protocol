package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper

data class BiomeLegacyWorldGenRulesData(
    val legacyPreHills: List<BiomeConditionalTransformationData>
) {
    companion object : ProtoCodec<BiomeLegacyWorldGenRulesData> {
        override fun serialize(value: BiomeLegacyWorldGenRulesData, stream: Sink) {
            ProtoHelper.serializeList(value.legacyPreHills, stream, BiomeConditionalTransformationData::serialize)
        }

        override fun deserialize(stream: Source): BiomeLegacyWorldGenRulesData {
            return BiomeLegacyWorldGenRulesData(
                legacyPreHills = ProtoHelper.deserializeList(stream, BiomeConditionalTransformationData::deserialize)
            )
        }
    }
}
