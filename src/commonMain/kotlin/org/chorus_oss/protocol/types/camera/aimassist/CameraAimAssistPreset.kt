package org.chorus_oss.protocol.types.camera.aimassist

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.String

data class CameraAimAssistPreset(
    val identifier: String,
    val exclusionList: List<String>,
    val liquidTargetingList: List<String>,
    val itemSettings: List<CameraAimAssistItemSettings>,
    val defaultItemSettings: String? = null,
    val handSettings: String? = null,
) {
    companion object : ProtoCodec<CameraAimAssistPreset> {
        override fun serialize(value: CameraAimAssistPreset, stream: Sink) {
            Proto.String.serialize(value.identifier, stream)
            ProtoHelper.serializeList(value.exclusionList, stream, Proto.String::serialize)
            ProtoHelper.serializeList(value.liquidTargetingList, stream, Proto.String::serialize)
            ProtoHelper.serializeList(value.itemSettings, stream, CameraAimAssistItemSettings::serialize)
            ProtoHelper.serializeNullable(value.defaultItemSettings, stream, Proto.String::serialize)
            ProtoHelper.serializeNullable(value.handSettings, stream, Proto.String::serialize)
        }

        override fun deserialize(stream: Source): CameraAimAssistPreset {
            return CameraAimAssistPreset(
                identifier = Proto.String.deserialize(stream),
                exclusionList = ProtoHelper.deserializeList(stream, Proto.String::deserialize),
                liquidTargetingList = ProtoHelper.deserializeList(stream, Proto.String::deserialize),
                itemSettings = ProtoHelper.deserializeList(stream, CameraAimAssistItemSettings::deserialize),
                defaultItemSettings = ProtoHelper.deserializeNullable(stream, Proto.String::deserialize),
                handSettings = ProtoHelper.deserializeNullable(stream, Proto.String::deserialize),
            )
        }
    }
}
