package org.chorus_oss.protocol.types.command

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Boolean

data class CommandOverload(
    val chaining: Boolean,
    val parameters: List<CommandParameter>
) {
    companion object : ProtoCodec<CommandOverload> {
        override fun serialize(value: CommandOverload, stream: Sink) {
            Proto.Boolean.serialize(value.chaining, stream)
            ProtoHelper.serializeList(value.parameters, stream, CommandParameter::serialize)
        }

        override fun deserialize(stream: Source): CommandOverload {
            return CommandOverload(
                chaining = Proto.Boolean.deserialize(stream),
                parameters = ProtoHelper.deserializeList(stream, CommandParameter::deserialize)
            )
        }
    }
}
