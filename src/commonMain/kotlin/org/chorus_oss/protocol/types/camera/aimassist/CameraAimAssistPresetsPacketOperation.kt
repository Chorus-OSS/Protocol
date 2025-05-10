package org.chorus_oss.protocol.types.camera.aimassist

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class CameraAimAssistPresetsPacketOperation {
    SET,
    ADD_TO_EXISTING;

    companion object : ProtoCodec<CameraAimAssistPresetsPacketOperation> {
        override fun serialize(value: CameraAimAssistPresetsPacketOperation, stream: Buffer) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Buffer): CameraAimAssistPresetsPacketOperation {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
