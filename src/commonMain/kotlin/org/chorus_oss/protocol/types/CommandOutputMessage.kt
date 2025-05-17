package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String


data class CommandOutputMessage(
    var internal: Boolean,
    var messageId: String,
    var parameters: List<String>
) {
    companion object : ProtoCodec<CommandOutputMessage> {
        override fun serialize(value: CommandOutputMessage, stream: Sink) {
            Proto.Boolean.serialize(value.internal, stream)
            Proto.String.serialize(value.messageId, stream)
            ProtoHelper.serializeList(value.parameters, stream, Proto.String)
        }

        override fun deserialize(stream: Source): CommandOutputMessage {
            return CommandOutputMessage(
                internal = Proto.Boolean.deserialize(stream),
                messageId = Proto.String.deserialize(stream),
                parameters = ProtoHelper.deserializeList(stream, Proto.String)
            )
        }
    }
}
