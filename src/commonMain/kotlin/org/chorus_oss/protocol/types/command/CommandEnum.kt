package org.chorus_oss.protocol.types.command

data class CommandEnum(
    val type: String,
    val valueIndices: List<UInt>
)
