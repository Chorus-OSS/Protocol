package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper

data class BiomeConsolidatedFeaturesData(
    val features: List<BiomeConsolidatedFeatureData>
) {
    companion object : ProtoCodec<BiomeConsolidatedFeaturesData> {
        override fun serialize(value: BiomeConsolidatedFeaturesData, stream: Sink) {
            ProtoHelper.serializeList(value.features, stream, BiomeConsolidatedFeatureData)
        }

        override fun deserialize(stream: Source): BiomeConsolidatedFeaturesData {
            return BiomeConsolidatedFeaturesData(
                features = ProtoHelper.deserializeList(stream, BiomeConsolidatedFeatureData)
            )
        }
    }
}
