package org.chorus_oss.protocol.types.camera.instruction

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Long
import org.chorus_oss.protocol.shared.types.Vector3f
import org.chorus_oss.protocol.types.ActorUniqueID

data class CameraTargetInstruction(
    val centerOffset: Vector3f? = null,
    val actorUniqueID: ActorUniqueID
) {
    companion object : ProtoCodec<CameraTargetInstruction> {
        override fun serialize(value: CameraTargetInstruction, stream: Buffer) {
            ProtoHelper.serializeNullable(value.centerOffset, stream, Vector3f::serialize)
            ProtoLE.Long.serialize(value.actorUniqueID.id, stream)
        }

        override fun deserialize(stream: Buffer): CameraTargetInstruction {
            return CameraTargetInstruction(
                centerOffset = ProtoHelper.deserializeNullable(stream, Vector3f::deserialize),
                actorUniqueID = ActorUniqueID(ProtoLE.Long.deserialize(stream))
            )
        }
    }
}
