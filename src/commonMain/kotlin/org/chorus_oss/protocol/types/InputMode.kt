package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt

enum class InputMode {
    Undefined,
    Mouse,
    Touch,
    GamePad,
    MotionController;

    companion object : ProtoCodec<InputMode> {
        override fun serialize(value: InputMode, stream: Sink) {
            ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
        }

        override fun deserialize(stream: Source): InputMode {
            return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
        }
    }
}
