package org.chorus_oss.protocol.types.camera.aimassist

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class CameraAimAssistItemSettings(
    val item: String,
    val category: String,
) {
    companion object : ProtoCodec<CameraAimAssistItemSettings> {
        override fun serialize(value: CameraAimAssistItemSettings, stream: Sink) {
            Proto.String.serialize(value.item, stream)
            Proto.String.serialize(value.category, stream)
        }

        override fun deserialize(stream: Source): CameraAimAssistItemSettings {
            return CameraAimAssistItemSettings(
                item = Proto.String.deserialize(stream),
                category = Proto.String.deserialize(stream),
            )
        }
    }
}
