package org.chorus_oss.protocol.types.camera.aimassist

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class ClientCameraAimAssistPacketAction {
    SetFromCameraPreset,
    Clear;

    companion object : ProtoCodec<ClientCameraAimAssistPacketAction> {
        override fun serialize(value: ClientCameraAimAssistPacketAction, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): ClientCameraAimAssistPacketAction {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
