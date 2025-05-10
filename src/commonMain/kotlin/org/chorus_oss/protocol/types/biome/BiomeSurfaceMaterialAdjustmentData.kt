package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper

data class BiomeSurfaceMaterialAdjustmentData(
    val adjustment: List<BiomeElementData>
) {
    companion object : ProtoCodec<BiomeSurfaceMaterialAdjustmentData> {
        override fun serialize(value: BiomeSurfaceMaterialAdjustmentData, stream: Buffer) {
            ProtoHelper.serializeList(value.adjustment, stream, BiomeElementData::serialize)
        }

        override fun deserialize(stream: Buffer): BiomeSurfaceMaterialAdjustmentData {
            return BiomeSurfaceMaterialAdjustmentData(
                adjustment = ProtoHelper.deserializeList(stream, BiomeElementData::deserialize)
            )
        }
    }
}
