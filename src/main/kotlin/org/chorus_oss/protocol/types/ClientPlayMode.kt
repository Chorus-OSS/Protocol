package org.chorus_oss.protocol.types

enum class ClientPlayMode {
    NORMAL,
    TEASER,
    SCREEN,
    VIEWER,
    REALITY,
    PLACEMENT,
    LIVING_ROOM,
    EXIT_LEVEL,
    EXIT_LEVEL_LIVING_ROOM;

    companion object {
        private val VALUES = entries.toTypedArray()

        fun fromOrdinal(ordinal: Int): ClientPlayMode {
            return VALUES[ordinal]
        }
    }
}