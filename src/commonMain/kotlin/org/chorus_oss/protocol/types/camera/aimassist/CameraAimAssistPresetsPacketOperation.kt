package org.chorus_oss.protocol.types.camera.aimassist

enum class CameraAimAssistPresetsPacketOperation {
    SET,
    ADD_TO_EXISTING;

    companion object {
        val VALUES: Array<CameraAimAssistPresetsPacketOperation> = entries.toTypedArray()
    }
}
