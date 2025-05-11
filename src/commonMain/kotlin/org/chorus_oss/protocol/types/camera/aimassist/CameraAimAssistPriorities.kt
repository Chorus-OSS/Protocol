package org.chorus_oss.protocol.types.camera.aimassist

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

data class CameraAimAssistPriorities(
    val entities: List<CameraAimAssistPriority>,
    val blocks: List<CameraAimAssistPriority>,
    val entityDefault: Int? = null,
    val blockDefault: Int? = null,
) {
    companion object : ProtoCodec<CameraAimAssistPriorities> {
        override fun serialize(value: CameraAimAssistPriorities, stream: Sink) {
            ProtoHelper.serializeList(value.entities, stream, CameraAimAssistPriority::serialize)
            ProtoHelper.serializeList(value.blocks, stream, CameraAimAssistPriority::serialize)
            ProtoHelper.serializeNullable(value.entityDefault, stream, ProtoLE.Int::serialize)
            ProtoHelper.serializeNullable(value.blockDefault, stream, ProtoLE.Int::serialize)
        }

        override fun deserialize(stream: Source): CameraAimAssistPriorities {
            return CameraAimAssistPriorities(
                entities = ProtoHelper.deserializeList(stream, CameraAimAssistPriority::deserialize),
                blocks = ProtoHelper.deserializeList(stream, CameraAimAssistPriority::deserialize),
                entityDefault = ProtoHelper.deserializeNullable(stream, ProtoLE.Int::deserialize),
                blockDefault = ProtoHelper.deserializeNullable(stream, ProtoLE.Int::deserialize)
            )
        }
    }
}
