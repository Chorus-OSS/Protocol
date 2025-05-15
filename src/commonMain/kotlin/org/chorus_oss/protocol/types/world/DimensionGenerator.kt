package org.chorus_oss.protocol.types.world

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class DimensionGenerator {
    Legacy,
    Overworld,
    Flat,
    Nether,
    End,
    Void;

    companion object : ProtoCodec<DimensionGenerator> {
        override fun serialize(value: DimensionGenerator, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): DimensionGenerator {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}