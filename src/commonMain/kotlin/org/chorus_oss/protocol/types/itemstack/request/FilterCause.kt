package org.chorus_oss.protocol.types.itemstack.request

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

enum class FilterCause {
    SERVER_CHAT_PUBLIC,
    SERVER_CHAT_WHISPER,
    SIGN_TEXT,
    ANVIL_TEXT,
    BOOK_AND_QUILL_TEXT,
    COMMAND_BLOCK_TEXT,
    BLOCK_ENTITY_DATA_TEXT,
    JOIN_EVENT_TEXT,
    LEAVE_EVENT_TEXT,
    SLASH_COMMAND_TEXT,
    CARTOGRAPHY_TEXT,
    SLASH_COMMAND_NON_CHAT,
    SCOREBOARD_TEXT,
    TICKING_AREA_TEXT;

    companion object : ProtoCodec<FilterCause> {
        override fun serialize(
            value: FilterCause,
            stream: Sink
        ) {
            ProtoLE.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): FilterCause {
            return entries[ProtoLE.Int.deserialize(stream)]
        }
    }
}