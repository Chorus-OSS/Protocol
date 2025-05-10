package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Short

data class BiomeElementData(
    val noiseFrequencyScale: Float,
    val noiseLowerBound: Float,
    val noiseUpperBound: Float,
    val heightMinType: ExpressionOp,
    val heightMin: Short,
    val heightMaxType: ExpressionOp,
    val heightMax: Short,
    val adjustedMaterials: BiomeSurfaceMaterialData,
) {
    companion object : ProtoCodec<BiomeElementData> {
        override fun serialize(value: BiomeElementData, stream: Buffer) {
            ProtoLE.Float.serialize(value.noiseFrequencyScale, stream)
            ProtoLE.Float.serialize(value.noiseLowerBound, stream)
            ProtoLE.Float.serialize(value.noiseUpperBound, stream)
            ExpressionOp.serialize(value.heightMinType, stream)
            ProtoLE.Short.serialize(value.heightMin, stream)
            ExpressionOp.serialize(value.heightMaxType, stream)
            ProtoLE.Short.serialize(value.heightMax, stream)
            BiomeSurfaceMaterialData.serialize(value.adjustedMaterials, stream)
        }

        override fun deserialize(stream: Buffer): BiomeElementData {
            return BiomeElementData(
                noiseFrequencyScale = ProtoLE.Float.deserialize(stream),
                noiseLowerBound = ProtoLE.Float.deserialize(stream),
                noiseUpperBound = ProtoLE.Float.deserialize(stream),
                heightMinType = ExpressionOp.deserialize(stream),
                heightMin = ProtoLE.Short.deserialize(stream),
                heightMaxType = ExpressionOp.deserialize(stream),
                heightMax = ProtoLE.Short.deserialize(stream),
                adjustedMaterials = BiomeSurfaceMaterialData.deserialize(stream)
            )
        }
    }
}
