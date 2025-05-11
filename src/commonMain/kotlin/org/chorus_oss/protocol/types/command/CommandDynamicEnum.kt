package org.chorus_oss.protocol.types.command

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.String

data class CommandDynamicEnum(
    val type: String,
    val values: List<String>
) {
    companion object : ProtoCodec<CommandDynamicEnum> {
        override fun serialize(value: CommandDynamicEnum, stream: Sink) {
            Proto.String.serialize(value.type, stream)
            ProtoHelper.serializeList(value.values, stream, Proto.String::serialize)
        }

        override fun deserialize(stream: Source): CommandDynamicEnum {
            return CommandDynamicEnum(
                type = Proto.String.deserialize(stream),
                values = ProtoHelper.deserializeList(stream, Proto.String::deserialize)
            )
        }
    }
}
