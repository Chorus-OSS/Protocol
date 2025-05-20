package org.chorus_oss.protocol.types.camera.instruction

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.Vector3f

data class CameraTargetInstruction(
    val centerOffset: Vector3f? = null,
    val actorUniqueID: ActorUniqueID
) {
    companion object : ProtoCodec<CameraTargetInstruction> {
        override fun serialize(value: CameraTargetInstruction, stream: Sink) {
            ProtoHelper.serializeNullable(value.centerOffset, stream, Vector3f)
            ProtoLE.Long.serialize(value.actorUniqueID.id, stream)
        }

        override fun deserialize(stream: Source): CameraTargetInstruction {
            return CameraTargetInstruction(
                centerOffset = ProtoHelper.deserializeNullable(stream, Vector3f),
                actorUniqueID = ActorUniqueID(ProtoLE.Long.deserialize(stream))
            )
        }
    }
}
