package org.chorus_oss.protocol.types.camera

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class CameraAudioListener {
    CAMERA,
    PLAYER;

    companion object : ProtoCodec<CameraAudioListener> {
        override fun serialize(value: CameraAudioListener, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): CameraAudioListener {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
