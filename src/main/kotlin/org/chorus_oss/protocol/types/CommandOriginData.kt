package org.chorus_oss.protocol.types

import java.util.*

data class CommandOriginData(
    val commandType: Origin,
    val commandUUID: UUID,
    val requestId: String, // event
    val commandData: Origin.CommandData?
) {
    enum class Origin {
        PLAYER,
        BLOCK,
        MINECART_BLOCK,
        DEV_CONSOLE,
        TEST,
        AUTOMATION_PLAYER,
        CLIENT_AUTOMATION,
        DEDICATED_SERVER,
        ENTITY,
        VIRTUAL,
        GAME_ARGUMENT,
        ENTITY_SERVER,
        PRECOMPILED,
        GAME_DIRECTOR_ENTITY_SERVER,
        SCRIPT,
        EXECUTE_CONTEXT;

        interface CommandData

        data class PlayerIDData(
            val playerID: Long,
        ) : CommandData

        companion object {
            fun fromOrdinal(ordinal: Int): Origin {
                return entries.getOrNull(ordinal)
                    ?: throw IllegalArgumentException("Unknown CommandOriginData Ordinal: $ordinal")
            }
        }
    }
}
