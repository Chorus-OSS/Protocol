package org.chorus_oss.protocol.types

enum class StructureAnimationMode {
    NONE,
    LAYER,
    BLOCKS;

    companion object {
        private val VALUES = entries.toTypedArray()

        fun from(id: Int): StructureAnimationMode {
            return VALUES[id]
        }
    }
}
