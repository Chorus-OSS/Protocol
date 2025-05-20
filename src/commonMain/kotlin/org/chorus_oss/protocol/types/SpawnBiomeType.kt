package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Short

enum class SpawnBiomeType {
    Default,
    UserDefined;

    companion object : ProtoCodec<SpawnBiomeType> {
        override fun serialize(value: SpawnBiomeType, stream: Sink) {
            ProtoLE.Short.serialize(value.ordinal.toShort(), stream)
        }

        override fun deserialize(stream: Source): SpawnBiomeType {
            return entries[ProtoLE.Short.deserialize(stream).toInt()]
        }
    }
}