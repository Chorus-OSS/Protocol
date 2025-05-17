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
import org.chorus_oss.protocol.types.Vector2f
import org.chorus_oss.protocol.types.Vector3f
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
            ProtoHelper.serializeNullable(value.posX, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.posY, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.posZ, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.rotX, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.rotY, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.rotSpeed, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.snapToTarget, stream, Proto.Boolean)
            ProtoHelper.serializeNullable(value.horizontalRotationLimit, stream, Vector2f)
            ProtoHelper.serializeNullable(value.verticalRotationLimit, stream, Vector2f)
            ProtoHelper.serializeNullable(value.continueTargeting, stream, Proto.Boolean)
            ProtoHelper.serializeNullable(value.trackingRadius, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.viewOffset, stream, Vector2f)
            ProtoHelper.serializeNullable(value.entityOffset, stream, Vector3f)
            ProtoHelper.serializeNullable(value.radius, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.minYawLimit, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.maxYawLimit, stream, ProtoLE.Float)
            ProtoHelper.serializeNullable(value.audioListener, stream, CameraAudioListener)
            ProtoHelper.serializeNullable(value.playerEffects, stream, Proto.Boolean)
            ProtoHelper.serializeNullable(value.alignTargetAndCameraForward, stream, Proto.Boolean)
            ProtoHelper.serializeNullable(value.aimAssist, stream, CameraPresetAimAssist)
            ProtoHelper.serializeNullable(value.controlScheme, stream, ControlScheme)
        }

        override fun deserialize(stream: Source): CameraPreset {
            return CameraPreset(
                name = Proto.String.deserialize(stream),
                parent = Proto.String.deserialize(stream),
                posX = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                posY = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                posZ = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                rotX = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                rotY = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                rotSpeed = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                snapToTarget = ProtoHelper.deserializeNullable(stream, Proto.Boolean),
                horizontalRotationLimit = ProtoHelper.deserializeNullable(stream, Vector2f),
                verticalRotationLimit = ProtoHelper.deserializeNullable(stream, Vector2f),
                continueTargeting = ProtoHelper.deserializeNullable(stream, Proto.Boolean),
                trackingRadius = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                viewOffset = ProtoHelper.deserializeNullable(stream, Vector2f),
                entityOffset = ProtoHelper.deserializeNullable(stream, Vector3f),
                radius = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                minYawLimit = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                maxYawLimit = ProtoHelper.deserializeNullable(stream, ProtoLE.Float),
                audioListener = ProtoHelper.deserializeNullable(stream, CameraAudioListener),
                playerEffects = ProtoHelper.deserializeNullable(stream, Proto.Boolean),
                alignTargetAndCameraForward = ProtoHelper.deserializeNullable(stream, Proto.Boolean),
                aimAssist = ProtoHelper.deserializeNullable(stream, CameraPresetAimAssist),
                controlScheme = ProtoHelper.deserializeNullable(stream, ControlScheme),
            )
        }
    }
}
