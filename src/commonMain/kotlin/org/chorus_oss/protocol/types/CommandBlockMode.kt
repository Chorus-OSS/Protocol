package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt

enum class CommandBlockMode {
    NORMAL,
    REPEATING,
    CHAIN;

    companion object : ProtoCodec<CommandBlockMode> {
        override fun serialize(value: CommandBlockMode, stream: Sink) {
            ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
        }

        override fun deserialize(stream: Source): CommandBlockMode {
            return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
        }
    }
}