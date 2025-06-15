package org.chorus_oss.protocol.types.camera.instruction

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.Vector2f
import org.chorus_oss.protocol.types.Vector3f
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
    val removeIgnoreStartingValuesComponent: Boolean,
) {
    companion object : ProtoCodec<CameraSetInstruction> {
        override fun serialize(value: CameraSetInstruction, stream: Sink) {
            ProtoLE.UInt.serialize(value.preset, stream)
            ProtoHelper.serializeNullable(value.ease, stream, CameraEase)
            ProtoHelper.serializeNullable(value.position, stream, Vector3f)
            ProtoHelper.serializeNullable(value.rotation, stream, Vector2f)
            ProtoHelper.serializeNullable(value.facing, stream, Vector3f)
            ProtoHelper.serializeNullable(value.viewOffset, stream, Vector2f)
            ProtoHelper.serializeNullable(value.entityOffset, stream, Vector3f)
            ProtoHelper.serializeNullable(value.default, stream, Proto.Boolean)
            Proto.Boolean.serialize(value.removeIgnoreStartingValuesComponent, stream)
        }

        override fun deserialize(stream: Source): CameraSetInstruction {
            return CameraSetInstruction(
                preset = ProtoLE.UInt.deserialize(stream),
                ease = ProtoHelper.deserializeNullable(stream, CameraEase),
                position = ProtoHelper.deserializeNullable(stream, Vector3f),
                rotation = ProtoHelper.deserializeNullable(stream, Vector2f),
                facing = ProtoHelper.deserializeNullable(stream, Vector3f),
                viewOffset = ProtoHelper.deserializeNullable(stream, Vector2f),
                entityOffset = ProtoHelper.deserializeNullable(stream, Vector3f),
                default = ProtoHelper.deserializeNullable(stream, Proto.Boolean),
                removeIgnoreStartingValuesComponent = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
