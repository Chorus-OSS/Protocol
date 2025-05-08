package org.chorus_oss.protocol.types

enum class StructureRotation {
    NONE,
    ROTATE_90,
    ROTATE_180,
    ROTATE_270;

    companion object {
        private val VALUES = entries.toTypedArray()

        fun from(id: Int): StructureRotation {
            return VALUES[id]
        }
    }
}
