package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class RandomDistributionType {
    SINGLE_VALUED,
    UNIFORM,
    GAUSSIAN,
    INVERSE_GAUSSIAN,
    FIXED_GRID,
    JITTERED_GRID,
    TRIANGLE;

    companion object : ProtoCodec<RandomDistributionType> {
        override fun serialize(value: RandomDistributionType, stream: Buffer) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Buffer): RandomDistributionType {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}