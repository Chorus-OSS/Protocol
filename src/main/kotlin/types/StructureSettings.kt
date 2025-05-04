package org.chorus_oss.protocol.types

import org.chorus_oss.chorus.math.BlockVector3
import org.chorus_oss.chorus.math.Vector3f

data class StructureSettings(
    val paletteName: String,
    val ignoringEntities: Boolean,
    val ignoringBlocks: Boolean,
    val nonTickingPlayersAndTickingAreasEnabled: Boolean,
    val size: BlockVector3,
    val offset: BlockVector3,
    val lastEditedByEntityId: Long,
    val rotation: StructureRotation,
    val mirror: StructureMirror,
    val animationMode: StructureAnimationMode,
    val animationSeconds: Float,
    val integrityValue: Float,
    val integritySeed: Int,
    val pivot: Vector3f,
)