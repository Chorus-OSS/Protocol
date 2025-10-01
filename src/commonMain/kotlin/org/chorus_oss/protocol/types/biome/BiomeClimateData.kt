package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float

data class BiomeClimateData(
    val temperature: Float,
    val downfall: Float,
    val snowAccumulationMin: Float,
    val snowAccumulationMax: Float
) {
    companion object : ProtoCodec<BiomeClimateData> {
        override fun serialize(value: BiomeClimateData, stream: Sink) {
            ProtoLE.Float.serialize(value.temperature, stream)
            ProtoLE.Float.serialize(value.downfall, stream)
            ProtoLE.Float.serialize(value.snowAccumulationMin, stream)
            ProtoLE.Float.serialize(value.snowAccumulationMax, stream)
        }

        override fun deserialize(stream: Source): BiomeClimateData {
            return BiomeClimateData(
                temperature = ProtoLE.Float.deserialize(stream),
                downfall = ProtoLE.Float.deserialize(stream),
                snowAccumulationMin = ProtoLE.Float.deserialize(stream),
                snowAccumulationMax = ProtoLE.Float.deserialize(stream)
            )
        }
    }
}
