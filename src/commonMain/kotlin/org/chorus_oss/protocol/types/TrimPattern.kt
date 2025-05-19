package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class TrimPattern(
    val itemName: String,
    val patternID: String
) {
    companion object : ProtoCodec<TrimPattern> {
        override fun serialize(value: TrimPattern, stream: Sink) {
            Proto.String.serialize(value.itemName, stream)
            Proto.String.serialize(value.patternID, stream)
        }

        override fun deserialize(stream: Source): TrimPattern {
            return TrimPattern(
                itemName = Proto.String.deserialize(stream),
                patternID = Proto.String.deserialize(stream)
            )
        }
    }
}
