package org.chorus_oss.protocol.types.camera.preset

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.shared.types.Vector2f
import org.chorus_oss.protocol.shared.types.Vector3f
import org.chorus_oss.protocol.types.ControlScheme
import org.chorus_oss.protocol.types.camera.CameraAudioListener

data class CameraPreset(
    val name: String,
    val parent: String,
    val posX: Float? = null,
    val posY: Float? = null,
    val posZ: Float? = null,
    val rotX: Float? = null,
    val rotY: Float? = null,
    val rotSpeed: Float? = null,
    val snapToTarget: Boolean? = null,
    val horizontalRotationLimit: Vector2f? = null,
    val verticalRotationLimit: Vector2f? = null,
    val continueTargeting: Boolean? = null,
    val trackingRadius: Float? = null,
    val viewOffset: Vector2f? = null,
    val entityOffset: Vector3f? = null,
    val radius: Float? = null,
    val minYawLimit: Float? = null,
    val maxYawLimit: Float? = null,
    val audioListener: CameraAudioListener? = null,
    val playerEffects: Boolean? = null,
    val alignTargetAndCameraForward: Boolean? = null,
    val aimAssist: CameraPresetAimAssist? = null,
    val controlScheme: ControlScheme? = null,
) {
    companion object : ProtoCodec<CameraPreset> {
        override fun serialize(value: CameraPreset, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            Proto.String.serialize(value.parent, stream)
            ProtoHelper.serializeNullable(value.posX, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.posY, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.posZ, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.rotX, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.rotY, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.rotSpeed, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.snapToTarget, stream, Proto.Boolean::serialize)
            ProtoHelper.serializeNullable(value.horizontalRotationLimit, stream, Vector2f::serialize)
            ProtoHelper.serializeNullable(value.verticalRotationLimit, stream, Vector2f::serialize)
            ProtoHelper.serializeNullable(value.continueTargeting, stream, Proto.Boolean::serialize)
            ProtoHelper.serializeNullable(value.trackingRadius, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.viewOffset, stream, Vector2f::serialize)
            ProtoHelper.serializeNullable(value.entityOffset, stream, Vector3f::serialize)
            ProtoHelper.serializeNullable(value.radius, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.minYawLimit, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.maxYawLimit, stream, ProtoLE.Float::serialize)
            ProtoHelper.serializeNullable(value.audioListener, stream, CameraAudioListener::serialize)
            ProtoHelper.serializeNullable(value.playerEffects, stream, Proto.Boolean::serialize)
            ProtoHelper.serializeNullable(value.alignTargetAndCameraForward, stream, Proto.Boolean::serialize)
            ProtoHelper.serializeNullable(value.aimAssist, stream, CameraPresetAimAssist::serialize)
            ProtoHelper.serializeNullable(value.controlScheme, stream, ControlScheme::serialize)
        }

        override fun deserialize(stream: Source): CameraPreset {
            return CameraPreset(
                name = Proto.String.deserialize(stream),
                parent = Proto.String.deserialize(stream),
                posX = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                posY = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                posZ = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                rotX = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                rotY = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                rotSpeed = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                snapToTarget = ProtoHelper.deserializeNullable(stream, Proto.Boolean::deserialize),
                horizontalRotationLimit = ProtoHelper.deserializeNullable(stream, Vector2f::deserialize),
                verticalRotationLimit = ProtoHelper.deserializeNullable(stream, Vector2f::deserialize),
                continueTargeting = ProtoHelper.deserializeNullable(stream, Proto.Boolean::deserialize),
                trackingRadius = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                viewOffset = ProtoHelper.deserializeNullable(stream, Vector2f::deserialize),
                entityOffset = ProtoHelper.deserializeNullable(stream, Vector3f::deserialize),
                radius = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                minYawLimit = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                maxYawLimit = ProtoHelper.deserializeNullable(stream, ProtoLE.Float::deserialize),
                audioListener = ProtoHelper.deserializeNullable(stream, CameraAudioListener::deserialize),
                playerEffects = ProtoHelper.deserializeNullable(stream, Proto.Boolean::deserialize),
                alignTargetAndCameraForward = ProtoHelper.deserializeNullable(stream, Proto.Boolean::deserialize),
                aimAssist = ProtoHelper.deserializeNullable(stream, CameraPresetAimAssist::deserialize),
                controlScheme = ProtoHelper.deserializeNullable(stream, ControlScheme::deserialize),
            )
        }
    }
}
