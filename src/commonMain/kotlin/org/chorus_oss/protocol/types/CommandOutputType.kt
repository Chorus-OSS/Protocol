package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class CommandOutputType {
    None,
    LastOutput,
    Silent,
    AllOutput,
    DataSet;

    companion object : ProtoCodec<CommandOutputType> {
        override fun serialize(value: CommandOutputType, stream: Sink) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): CommandOutputType {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
