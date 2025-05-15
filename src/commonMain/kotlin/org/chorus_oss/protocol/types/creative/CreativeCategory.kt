package org.chorus_oss.protocol.types.creative

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

enum class CreativeCategory {
    All,
    Construction,
    Nature,
    EQUIPMENT,
    Items,
    ItemCommandOnly,
    Undefined;

    companion object : ProtoCodec<CreativeCategory> {
        override fun serialize(
            value: CreativeCategory,
            stream: Sink
        ) {
            ProtoLE.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): CreativeCategory {
            return entries[ProtoLE.Int.deserialize(stream)]
        }
    }
}