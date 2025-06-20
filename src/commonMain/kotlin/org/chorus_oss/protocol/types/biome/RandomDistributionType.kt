package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class RandomDistributionType {
    SingleValued,
    Uniform,
    Gaussian,
    InverseGaussian,
    FixedGrid,
    JitteredGrid,
    Triangle;

    companion object : ProtoCodec<RandomDistributionType> {
        override fun serialize(value: RandomDistributionType, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): RandomDistributionType {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}