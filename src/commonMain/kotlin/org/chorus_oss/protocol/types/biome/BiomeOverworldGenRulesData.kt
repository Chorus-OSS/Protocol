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
            ProtoHelper.serializeList(value.hillsTransformations, stream, BiomeWeightedData)
            ProtoHelper.serializeList(value.mutateTransformations, stream, BiomeWeightedData)
            ProtoHelper.serializeList(value.riverTransformations, stream, BiomeWeightedData)
            ProtoHelper.serializeList(value.shoreTransformations, stream, BiomeWeightedData)
            ProtoHelper.serializeList(value.preHillsEdge, stream, BiomeConditionalTransformationData)
            ProtoHelper.serializeList(value.postShoreEdge, stream, BiomeConditionalTransformationData)
            ProtoHelper.serializeList(value.climate, stream, BiomeWeightedTemperatureData)
        }

        override fun deserialize(stream: Source): BiomeOverworldGenRulesData {
            return BiomeOverworldGenRulesData(
                hillsTransformations = ProtoHelper.deserializeList(stream, BiomeWeightedData),
                mutateTransformations = ProtoHelper.deserializeList(stream, BiomeWeightedData),
                riverTransformations = ProtoHelper.deserializeList(stream, BiomeWeightedData),
                shoreTransformations = ProtoHelper.deserializeList(stream, BiomeWeightedData),
                preHillsEdge = ProtoHelper.deserializeList(stream, BiomeConditionalTransformationData),
                postShoreEdge = ProtoHelper.deserializeList(stream, BiomeConditionalTransformationData),
                climate = ProtoHelper.deserializeList(stream, BiomeWeightedTemperatureData),
            )
        }
    }
}
