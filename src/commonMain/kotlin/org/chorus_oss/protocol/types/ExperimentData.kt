package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String

data class ExperimentData(
    val name: String,
    val enabled: Boolean,
) {
    companion object : ProtoCodec<ExperimentData> {
        override fun serialize(value: ExperimentData, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            Proto.Boolean.serialize(value.enabled, stream)
        }

        override fun deserialize(stream: Source): ExperimentData {
            return ExperimentData(
                name = Proto.String.deserialize(stream),
                enabled = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
