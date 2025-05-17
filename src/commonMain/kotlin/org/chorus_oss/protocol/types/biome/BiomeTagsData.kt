package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Short

data class BiomeTagsData(
    val tags: List<Short>
) {
    companion object : ProtoCodec<BiomeTagsData> {
        override fun serialize(value: BiomeTagsData, stream: Sink) {
            ProtoHelper.serializeList(value.tags, stream, ProtoLE.Short)
        }

        override fun deserialize(stream: Source): BiomeTagsData {
            return BiomeTagsData(
                tags = ProtoHelper.deserializeList(stream, ProtoLE.Short)
            )
        }
    }
}
