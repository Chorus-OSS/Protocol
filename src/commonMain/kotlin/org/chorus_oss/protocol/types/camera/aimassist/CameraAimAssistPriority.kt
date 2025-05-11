package org.chorus_oss.protocol.types.camera.aimassist

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String

data class CameraAimAssistPriority(
    val identifier: String,
    val priority: Int,
) {
    companion object : ProtoCodec<CameraAimAssistPriority> {
        override fun serialize(value: CameraAimAssistPriority, stream: Sink) {
            Proto.String.serialize(value.identifier, stream)
            ProtoLE.Int.serialize(value.priority, stream)
        }

        override fun deserialize(stream: Source): CameraAimAssistPriority {
            return CameraAimAssistPriority(
                identifier = Proto.String.deserialize(stream),
                priority = ProtoLE.Int.deserialize(stream)
            )
        }
    }
}
