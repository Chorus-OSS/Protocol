package org.chorus_oss.protocol.types.itemstack.request

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

enum class FilterCause {
    ServerChatPublic,
    ServerChatWhisper,
    SignText,
    AnvilText,
    BookAndQuillText,
    CommandBlockText,
    BlockEntityDataText,
    JoinEventText,
    LeaveEventText,
    SlashCommandText,
    CartographyText,
    SlashCommandNonChat,
    ScoreboardText,
    TickingAreaText;

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