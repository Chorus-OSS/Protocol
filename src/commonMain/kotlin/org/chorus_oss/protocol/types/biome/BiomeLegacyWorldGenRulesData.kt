package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper

data class BiomeLegacyWorldGenRulesData(
    val legacyPreHills: List<BiomeConditionalTransformationData>
) {
    companion object : ProtoCodec<BiomeLegacyWorldGenRulesData> {
        override fun serialize(value: BiomeLegacyWorldGenRulesData, stream: Buffer) {
            ProtoHelper.serializeList(value.legacyPreHills, stream, BiomeConditionalTransformationData::serialize)
        }

        override fun deserialize(stream: Buffer): BiomeLegacyWorldGenRulesData {
            return BiomeLegacyWorldGenRulesData(
                legacyPreHills = ProtoHelper.deserializeList(stream, BiomeConditionalTransformationData::deserialize)
            )
        }
    }
}
