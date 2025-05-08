package org.chorus_oss.protocol.types

enum class CommandEnumConstraintType {
    CHEATS_ENABLED,
    OPERATOR_PERMISSIONS,
    HOST_PERMISSIONS,
    UNKNOWN_3;

    companion object {
        private val VALUES = entries.toTypedArray()

        fun byId(id: Int): CommandEnumConstraintType {
            if (id >= 0 && id < VALUES.size) {
                return VALUES[id]
            }
            throw UnsupportedOperationException("Unknown CommandEnumConstraintType ID: $id")
        }
    }
}
