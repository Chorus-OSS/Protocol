package org.chorus_oss.chorus.network.protocol

import org.chorus_oss.chorus.camera.data.CameraPreset
import org.chorus_oss.chorus.network.connection.util.HandleByteBuf
import org.chorus_oss.chorus.network.protocol.types.camera.aimassist.CameraAimAssist
import org.chorus_oss.chorus.network.protocol.types.camera.aimassist.CameraPresetAimAssist
import org.chorus_oss.chorus.utils.OptionalValue

data class CameraPresetsPacket(
    val presets: MutableList<CameraPreset>
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeUnsignedVarInt(presets.size)
        for (p in presets) {
            writePreset(byteBuf, p)
        }
    }

    fun writePreset(byteBuf: HandleByteBuf, preset: CameraPreset) {
        byteBuf.writeString(preset.identifier)
        byteBuf.writeString(preset.inheritFrom)
        byteBuf.writeNotNull(preset.pos) { v -> byteBuf.writeFloatLE(v.x) }
        byteBuf.writeNotNull(preset.pos) { v -> byteBuf.writeFloatLE(v.y) }
        byteBuf.writeNotNull(preset.pos) { v -> byteBuf.writeFloatLE(v.z) }
        byteBuf.writeNotNull(preset.pitch) { value -> byteBuf.writeFloatLE(value) }
        byteBuf.writeNotNull(preset.yaw) { value -> byteBuf.writeFloatLE(value) }
        byteBuf.writeNotNull(preset.rotationSpeed) { value -> byteBuf.writeFloatLE(value) }
        byteBuf.writeOptional(preset.snapToTarget) { value -> byteBuf.writeBoolean(value) }
        byteBuf.writeNotNull(preset.horizontalRotationLimit) { byteBuf.writeVector2f(it) }
        byteBuf.writeNotNull(preset.verticalRotationLimit) { byteBuf.writeVector2f(it) }
        byteBuf.writeOptional(preset.continueTargeting) { value -> byteBuf.writeBoolean(value) }
        byteBuf.writeOptional(preset.blockListeningRadius) { value -> byteBuf.writeFloatLE(value) }
        byteBuf.writeNotNull(preset.viewOffset, byteBuf::writeVector2f)
        byteBuf.writeNotNull(preset.entityOffset, byteBuf::writeVector3f)
        byteBuf.writeNotNull(preset.radius) { value -> byteBuf.writeFloatLE(value) }
        byteBuf.writeNotNull(preset.yawLimitMin) { value -> byteBuf.writeFloatLE(value) }
        byteBuf.writeNotNull(preset.yawLimitMax) { value -> byteBuf.writeFloatLE(value) }
        byteBuf.writeNotNull(preset.listener) { l -> byteBuf.writeByte(l.ordinal) }
        byteBuf.writeOptional(preset.playEffect) { value -> byteBuf.writeBoolean(value) }
        byteBuf.writeOptional(preset.alignTargetAndCameraForward) { value -> byteBuf.writeBoolean(value) }
        writeCameraPresetAimAssist(byteBuf, preset.aimAssist)
        byteBuf.writeNotNull(preset.controlScheme) { value -> byteBuf.writeByte(value.ordinal) }
    }

    fun writeCameraPresetAimAssist(byteBuf: HandleByteBuf, data: OptionalValue<CameraPresetAimAssist>) {
        val present = data.isPresent
        byteBuf.writeBoolean(present)
        if (present) {
            val value = data.get()!!
            byteBuf.writeOptional(value.presetId) { byteBuf.writeString(it) }
            writeTargetMode(byteBuf, value.targetMode)
            byteBuf.writeOptional(value.angle) { byteBuf.writeVector2f(it) }
            byteBuf.writeOptional(value.distance) { byteBuf.writeFloatLE(it) }
        }
    }

    private fun writeTargetMode(byteBuf: HandleByteBuf, data: OptionalValue<CameraAimAssist>) {
        val present = data.isPresent
        byteBuf.writeBoolean(present)
        if (present) {
            byteBuf.writeIntLE(data.get()!!.ordinal)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.Companion.CAMERA_PRESETS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
