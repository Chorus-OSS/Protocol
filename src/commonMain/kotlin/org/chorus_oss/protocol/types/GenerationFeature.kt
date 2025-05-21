package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class GenerationFeature(
    val name: String,
    val json: String,
) {
    companion object : ProtoCodec<GenerationFeature> {
        override fun serialize(value: GenerationFeature, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            Proto.String.serialize(value.json, stream)
        }

        override fun deserialize(stream: Source): GenerationFeature {
            return GenerationFeature(
                name = Proto.String.deserialize(stream),
                json = Proto.String.deserialize(stream),
            )
        }
    }
}
