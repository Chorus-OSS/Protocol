package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Short
import org.chorus_oss.protocol.core.types.UInt

data class BiomeWeightedData(
    val biome: Short,
    val weight: UInt,
) {
    companion object : ProtoCodec<BiomeWeightedData> {
        override fun serialize(value: BiomeWeightedData, stream: Sink) {
            ProtoLE.Short.serialize(value.biome, stream)
            ProtoLE.UInt.serialize(value.weight, stream)
        }

        override fun deserialize(stream: Source): BiomeWeightedData {
            return BiomeWeightedData(
                biome = ProtoLE.Short.deserialize(stream),
                weight = ProtoLE.UInt.deserialize(stream)
            )
        }
    }
}
