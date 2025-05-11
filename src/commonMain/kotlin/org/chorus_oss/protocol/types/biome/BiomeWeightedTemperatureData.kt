package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.UInt

data class BiomeWeightedTemperatureData(
    val temperature: BiomeTemperatureCategory,
    val weight: UInt,
) {
    companion object : ProtoCodec<BiomeWeightedTemperatureData> {
        override fun serialize(value: BiomeWeightedTemperatureData, stream: Sink) {
            BiomeTemperatureCategory.serialize(value.temperature, stream)
            ProtoLE.UInt.serialize(value.weight, stream)
        }

        override fun deserialize(stream: Source): BiomeWeightedTemperatureData {
            return BiomeWeightedTemperatureData(
                temperature = BiomeTemperatureCategory.deserialize(stream),
                weight = ProtoLE.UInt.deserialize(stream)
            )
        }
    }
}
