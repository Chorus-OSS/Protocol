package org.chorus_oss.protocol.types

enum class StructureRedstoneSaveMode {
    SAVES_TO_MEMORY,
    SAVES_TO_DISK;

    companion object {
        private val VALUES = entries.toTypedArray()

        fun from(id: Int): StructureRedstoneSaveMode {
            return VALUES[id]
        }
    }
}
