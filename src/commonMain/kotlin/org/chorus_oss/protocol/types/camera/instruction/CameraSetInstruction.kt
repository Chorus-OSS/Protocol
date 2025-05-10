package org.chorus_oss.protocol.types.camera.instruction

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.shared.types.Vector2f
import org.chorus_oss.protocol.shared.types.Vector3f
import org.chorus_oss.protocol.types.camera.CameraEase

data class CameraSetInstruction(
    val preset: UInt,
    val ease: CameraEase? = null,
    val position: Vector3f? = null,
    val rotation: Vector2f? = null,
    val facing: Vector3f? = null,
    val viewOffset: Vector2f? = null,
    val entityOffset: Vector3f? = null,
    val default: Boolean? = null,
) {
    companion object : ProtoCodec<CameraSetInstruction> {
        override fun serialize(value: CameraSetInstruction, stream: Buffer) {
            ProtoLE.UInt.serialize(value.preset, stream)
            ProtoHelper.serializeNullable(value.ease, stream, CameraEase::serialize)
            ProtoHelper.serializeNullable(value.position, stream, Vector3f::serialize)
            ProtoHelper.serializeNullable(value.rotation, stream, Vector2f::serialize)
            ProtoHelper.serializeNullable(value.facing, stream, Vector3f::serialize)
            ProtoHelper.serializeNullable(value.viewOffset, stream, Vector2f::serialize)
            ProtoHelper.serializeNullable(value.entityOffset, stream, Vector3f::serialize)
            ProtoHelper.serializeNullable(value.default, stream, Proto.Boolean::serialize)
        }

        override fun deserialize(stream: Buffer): CameraSetInstruction {
            return CameraSetInstruction(
                preset = ProtoLE.UInt.deserialize(stream),
                ease = ProtoHelper.deserializeNullable(stream, CameraEase::deserialize),
                position = ProtoHelper.deserializeNullable(stream, Vector3f::deserialize),
                rotation = ProtoHelper.deserializeNullable(stream, Vector2f::deserialize),
                facing = ProtoHelper.deserializeNullable(stream, Vector3f::deserialize),
                viewOffset = ProtoHelper.deserializeNullable(stream, Vector2f::deserialize),
                entityOffset = ProtoHelper.deserializeNullable(stream, Vector3f::deserialize),
                default = ProtoHelper.deserializeNullable(stream, Proto.Boolean::deserialize)
            )
        }
    }
}
