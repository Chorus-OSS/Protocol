package org.chorus_oss.protocol.types

import org.chorus_oss.chorus.block.property.enums.StructureBlockType

data class StructureEditorData(
    val name: String,
    val filteredName: String,
    val dataField: String,
    val includingPlayers: Boolean,
    val boundingBoxVisible: Boolean,
    val type: StructureBlockType,
    val settings: StructureSettings,
    val redstoneSaveMode: StructureRedstoneSaveMode,
)
