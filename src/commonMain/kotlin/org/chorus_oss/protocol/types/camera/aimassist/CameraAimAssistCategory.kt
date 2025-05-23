package org.chorus_oss.protocol.types.camera.aimassist

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class CameraAimAssistCategory(
    val name: String,
    val priorities: CameraAimAssistPriorities,
) {
    companion object : ProtoCodec<CameraAimAssistCategory> {
        override fun serialize(value: CameraAimAssistCategory, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            CameraAimAssistPriorities.serialize(value.priorities, stream)
        }

        override fun deserialize(stream: Source): CameraAimAssistCategory {
            return CameraAimAssistCategory(
                name = Proto.String.deserialize(stream),
                priorities = CameraAimAssistPriorities.deserialize(stream)
            )
        }
    }
}
