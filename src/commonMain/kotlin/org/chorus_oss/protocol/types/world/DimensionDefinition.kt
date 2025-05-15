package org.chorus_oss.protocol.types.world

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String

data class DimensionDefinition(
    val name: String,
    val rangeMin: Int,
    val rangeMax: Int,
    val generator: DimensionGenerator,
) {
    companion object : ProtoCodec<DimensionDefinition> {
        override fun serialize(
            value: DimensionDefinition,
            stream: Sink
        ) {
            Proto.String.serialize(value.name, stream)
            ProtoVAR.Int.serialize(value.rangeMin, stream)
            ProtoVAR.Int.serialize(value.rangeMax, stream)
            DimensionGenerator.serialize(value.generator, stream)
        }

        override fun deserialize(stream: Source): DimensionDefinition {
            return DimensionDefinition(
                name = Proto.String.deserialize(stream),
                rangeMin = ProtoVAR.Int.deserialize(stream),
                rangeMax = ProtoVAR.Int.deserialize(stream),
                generator = DimensionGenerator.deserialize(stream)
            )
        }
    }
}
