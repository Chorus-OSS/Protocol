package org.chorus_oss.protocol.types.itemstack.request

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
}