package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper

data class BiomeOverworldGenRulesData(
    val hillsTransformations: List<BiomeWeightedData>,
    val mutateTransformations: List<BiomeWeightedData>,
    val riverTransformations: List<BiomeWeightedData>,
    val shoreTransformations: List<BiomeWeightedData>,
    val preHillsEdge: List<BiomeConditionalTransformationData>,
    val postShoreEdge: List<BiomeConditionalTransformationData>,
    val climate: List<BiomeWeightedTemperatureData>,
) {
    companion object : ProtoCodec<BiomeOverworldGenRulesData> {
        override fun serialize(value: BiomeOverworldGenRulesData, stream: Sink) {
            ProtoHelper.serializeList(value.hillsTransformations, stream, BiomeWeightedData::serialize)
            ProtoHelper.serializeList(value.mutateTransformations, stream, BiomeWeightedData::serialize)
            ProtoHelper.serializeList(value.riverTransformations, stream, BiomeWeightedData::serialize)
            ProtoHelper.serializeList(value.shoreTransformations, stream, BiomeWeightedData::serialize)
            ProtoHelper.serializeList(value.preHillsEdge, stream, BiomeConditionalTransformationData::serialize)
            ProtoHelper.serializeList(value.postShoreEdge, stream, BiomeConditionalTransformationData::serialize)
            ProtoHelper.serializeList(value.climate, stream, BiomeWeightedTemperatureData::serialize)
        }

        override fun deserialize(stream: Source): BiomeOverworldGenRulesData {
            return BiomeOverworldGenRulesData(
                hillsTransformations = ProtoHelper.deserializeList(stream, BiomeWeightedData::deserialize),
                mutateTransformations = ProtoHelper.deserializeList(stream, BiomeWeightedData::deserialize),
                riverTransformations = ProtoHelper.deserializeList(stream, BiomeWeightedData::deserialize),
                shoreTransformations = ProtoHelper.deserializeList(stream, BiomeWeightedData::deserialize),
                preHillsEdge = ProtoHelper.deserializeList(stream, BiomeConditionalTransformationData::deserialize),
                postShoreEdge = ProtoHelper.deserializeList(stream, BiomeConditionalTransformationData::deserialize),
                climate = ProtoHelper.deserializeList(stream, BiomeWeightedTemperatureData::deserialize),
            )
        }
    }
}
