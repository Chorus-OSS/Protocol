package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper

data class BiomeConsolidatedFeaturesData(
    val features: List<BiomeConsolidatedFeatureData>
) {
    companion object : ProtoCodec<BiomeConsolidatedFeaturesData> {
        override fun serialize(value: BiomeConsolidatedFeaturesData, stream: Buffer) {
            ProtoHelper.serializeList(value.features, stream, BiomeConsolidatedFeatureData::serialize)
        }

        override fun deserialize(stream: Buffer): BiomeConsolidatedFeaturesData {
            return BiomeConsolidatedFeaturesData(
                features = ProtoHelper.deserializeList(stream, BiomeConsolidatedFeatureData::deserialize)
            )
        }
    }
}
