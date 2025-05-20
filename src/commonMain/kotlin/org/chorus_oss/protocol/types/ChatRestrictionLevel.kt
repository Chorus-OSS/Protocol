package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class ChatRestrictionLevel {
    None,
    Dropped,
    Disabled;

    companion object : ProtoCodec<ChatRestrictionLevel> {
        override fun serialize(value: ChatRestrictionLevel, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): ChatRestrictionLevel {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}