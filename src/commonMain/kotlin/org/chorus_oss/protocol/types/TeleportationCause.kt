package org.chorus_oss.protocol.types

import io.netty.util.internal.logging.InternalLogger
import io.netty.util.internal.logging.InternalLoggerFactory

enum class TeleportationCause {
    UNKNOWN,
    PROJECTILE,
    CHORUS_FRUIT,
    COMMAND,
    BEHAVIOR;

    companion object {
        private val log: InternalLogger = InternalLoggerFactory.getInstance(TeleportationCause::class.java)

        private val VALUES = entries.toTypedArray()

        fun byId(id: Int): TeleportationCause {
            return VALUES.getOrNull(id) ?: run {
                log.debug("Unknown teleportation cause ID: {}", id)
                UNKNOWN
            }
        }
    }
}
