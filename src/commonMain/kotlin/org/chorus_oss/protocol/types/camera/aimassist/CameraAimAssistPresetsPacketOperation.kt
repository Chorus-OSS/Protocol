package org.chorus_oss.protocol.types.camera.aimassist

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class CameraAimAssistPresetsPacketOperation {
    SET,
    ADD_TO_EXISTING;

    companion object : ProtoCodec<CameraAimAssistPresetsPacketOperation> {
        override fun serialize(value: CameraAimAssistPresetsPacketOperation, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): CameraAimAssistPresetsPacketOperation {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
