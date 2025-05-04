package org.chorus_oss.protocol.types.camera.aimassist

data class CameraAimAssistCategoryPriorities(
    val entities: Map<String, Int>,
    val blocks: Map<String, Int>,
)
