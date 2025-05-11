package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper

data class BiomeSurfaceMaterialAdjustmentData(
    val adjustment: List<BiomeElementData>
) {
    companion object : ProtoCodec<BiomeSurfaceMaterialAdjustmentData> {
        override fun serialize(value: BiomeSurfaceMaterialAdjustmentData, stream: Sink) {
            ProtoHelper.serializeList(value.adjustment, stream, BiomeElementData::serialize)
        }

        override fun deserialize(stream: Source): BiomeSurfaceMaterialAdjustmentData {
            return BiomeSurfaceMaterialAdjustmentData(
                adjustment = ProtoHelper.deserializeList(stream, BiomeElementData::deserialize)
            )
        }
    }
}
