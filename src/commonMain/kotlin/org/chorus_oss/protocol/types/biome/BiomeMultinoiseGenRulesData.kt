package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float

data class BiomeMultinoiseGenRulesData(
    val temperature: Float,
    val humidity: Float,
    val altitude: Float,
    val weirdness: Float,
    val weight: Float,
) {
    companion object : ProtoCodec<BiomeMultinoiseGenRulesData> {
        override fun serialize(value: BiomeMultinoiseGenRulesData, stream: Buffer) {
            ProtoLE.Float.serialize(value.temperature, stream)
            ProtoLE.Float.serialize(value.humidity, stream)
            ProtoLE.Float.serialize(value.altitude, stream)
            ProtoLE.Float.serialize(value.weirdness, stream)
            ProtoLE.Float.serialize(value.weight, stream)
        }

        override fun deserialize(stream: Buffer): BiomeMultinoiseGenRulesData {
            return BiomeMultinoiseGenRulesData(
                temperature = ProtoLE.Float.deserialize(stream),
                humidity = ProtoLE.Float.deserialize(stream),
                altitude = ProtoLE.Float.deserialize(stream),
                weirdness = ProtoLE.Float.deserialize(stream),
                weight = ProtoLE.Float.deserialize(stream)
            )
        }
    }
}
