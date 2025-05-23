package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class ControlScheme {
    LockedPlayerRelativeStrafe,
    CameraRelative,
    CameraRelativeStrafe,
    PlayerRelative,
    PlayerRelativeStrafe;

    companion object : ProtoCodec<ControlScheme> {
        override fun serialize(value: ControlScheme, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): ControlScheme {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}