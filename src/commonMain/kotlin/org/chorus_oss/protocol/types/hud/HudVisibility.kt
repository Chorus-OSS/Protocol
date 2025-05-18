package org.chorus_oss.protocol.types.hud

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class HudVisibility {
    Hide,
    Reset;

    companion object : ProtoCodec<HudVisibility> {
        override fun serialize(value: HudVisibility, stream: Sink) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): HudVisibility {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}
