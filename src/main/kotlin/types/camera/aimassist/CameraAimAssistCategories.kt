package org.chorus_oss.protocol.types.camera.aimassist

data class CameraAimAssistCategories(
    val identifier: String,
    val categories: List<CameraAimAssistCategory>,
)
