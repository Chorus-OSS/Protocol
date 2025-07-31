package org.chorus_oss.protocol.types.camera.instruction

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.types.camera.CameraEase

data class CameraFieldOfViewInstruction(
    val fieldOfView: Float,
    val easeTime: Float,
    val easeType: CameraEase.Companion.Type,
    val clear: Boolean,
) {
    companion object : ProtoCodec<CameraFieldOfViewInstruction> {
        override fun serialize(
            value: CameraFieldOfViewInstruction,
            stream: Sink
        ) {
            ProtoLE.Float.serialize(value.fieldOfView, stream)
            ProtoLE.Float.serialize(value.easeTime, stream)
            CameraEase.Companion.Type.serialize(value.easeType, stream)
            Proto.Boolean.serialize(value.clear, stream)
        }

        override fun deserialize(stream: Source): CameraFieldOfViewInstruction {
            return CameraFieldOfViewInstruction(
                fieldOfView = ProtoLE.Float.deserialize(stream),
                easeTime = ProtoLE.Float.deserialize(stream),
                easeType = CameraEase.Companion.Type.deserialize(stream),
                clear = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
