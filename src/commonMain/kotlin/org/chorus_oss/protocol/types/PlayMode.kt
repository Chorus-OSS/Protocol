package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt

enum class PlayMode {
    Normal,
    Teaser,
    Screen,
    Viewer,
    Reality,
    Placement,
    LivingRoom,
    ExitLevel,
    ExitLevelLivingRoom;

    companion object : ProtoCodec<PlayMode> {
        override fun serialize(value: PlayMode, stream: Sink) {
            ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
        }

        override fun deserialize(stream: Source): PlayMode {
            return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
        }
    }
}