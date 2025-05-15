package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class EducationExternalLinkSettings(
    val url: String,
    val displayName: String,
) {
    companion object : ProtoCodec<EducationExternalLinkSettings> {
        override fun serialize(
            value: EducationExternalLinkSettings,
            stream: Sink
        ) {
            Proto.String.serialize(value.url, stream)
            Proto.String.serialize(value.displayName, stream)
        }

        override fun deserialize(stream: Source): EducationExternalLinkSettings {
            return EducationExternalLinkSettings(
                url = Proto.String.deserialize(stream),
                displayName = Proto.String.deserialize(stream)
            )
        }
    }
}
