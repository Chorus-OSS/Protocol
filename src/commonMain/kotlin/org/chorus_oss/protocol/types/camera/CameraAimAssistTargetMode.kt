package org.chorus_oss.protocol.types.camera

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class CameraAimAssistTargetMode {
    ANGLE,
    DISTANCE;

    companion object : ProtoCodec<CameraAimAssistTargetMode> {
        override fun serialize(value: CameraAimAssistTargetMode, stream: Buffer) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Buffer): CameraAimAssistTargetMode {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
