package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class EduSharedUriResource(
    val buttonName: String,
    val linkUri: String
) {
    companion object : ProtoCodec<EduSharedUriResource> {
        override fun serialize(value: EduSharedUriResource, stream: Sink) {
            Proto.String.serialize(value.buttonName, stream)
            Proto.String.serialize(value.linkUri, stream)
        }

        override fun deserialize(stream: Source): EduSharedUriResource {
            return EduSharedUriResource(
                buttonName = Proto.String.deserialize(stream),
                linkUri = Proto.String.deserialize(stream)
            )
        }
    }
}
