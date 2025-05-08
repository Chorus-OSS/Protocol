package org.chorus_oss.protocol.types

enum class MovementEffectType(val id: Int) {
    INVALID(-1),
    GLIDE_BOOST(0);

    companion object {
        private val VALUES = entries.toTypedArray()

        fun byId(id: Int): MovementEffectType? {
            return VALUES.find { it.id == id }
        }
    }
}
