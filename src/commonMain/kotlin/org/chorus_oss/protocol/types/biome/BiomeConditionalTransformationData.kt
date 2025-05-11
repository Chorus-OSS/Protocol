package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Short
import org.chorus_oss.protocol.core.types.UInt

data class BiomeConditionalTransformationData(
    val weightedBiome: List<BiomeWeightedData>,
    val conditionJSON: Short,
    val minPassingNeighbors: UInt,
) {
    companion object : ProtoCodec<BiomeConditionalTransformationData> {
        override fun serialize(value: BiomeConditionalTransformationData, stream: Sink) {
            ProtoHelper.serializeList(value.weightedBiome, stream, BiomeWeightedData::serialize)
            ProtoLE.Short.serialize(value.conditionJSON, stream)
            ProtoLE.UInt.serialize(value.minPassingNeighbors, stream)
        }

        override fun deserialize(stream: Source): BiomeConditionalTransformationData {
            return BiomeConditionalTransformationData(
                weightedBiome = ProtoHelper.deserializeList(stream, BiomeWeightedData::deserialize),
                conditionJSON = ProtoLE.Short.deserialize(stream),
                minPassingNeighbors = ProtoLE.UInt.deserialize(stream)
            )
        }
    }
}
