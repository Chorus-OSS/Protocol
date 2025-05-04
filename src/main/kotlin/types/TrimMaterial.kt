package org.chorus_oss.protocol.types

@JvmRecord
data class TrimMaterial(
    val materialId: String,
    val color: String,
    val itemName: String
)
