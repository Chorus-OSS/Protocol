package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
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
        override fun serialize(value: BiomeTemperatureCategory, stream: Buffer) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Buffer): BiomeTemperatureCategory {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}