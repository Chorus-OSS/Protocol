package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class BiomeTemperatureCategory {
    MEDIUM,
    WARM,
    LUKEWARM,
    COLD,
    FROZEN;

    companion object : ProtoCodec<BiomeTemperatureCategory> {
        override fun serialize(value: BiomeTemperatureCategory, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): BiomeTemperatureCategory {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}