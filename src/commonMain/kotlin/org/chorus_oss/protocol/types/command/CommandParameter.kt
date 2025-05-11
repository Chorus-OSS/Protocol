package org.chorus_oss.protocol.types.command

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt

data class CommandParameter(
    val name: String,
    val typeFlags: UInt,
    val optional: Boolean,
    val options: Byte,
) {
    companion object : ProtoCodec<CommandParameter> {
        override fun serialize(value: CommandParameter, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            ProtoLE.UInt.serialize(value.typeFlags, stream)
            Proto.Boolean.serialize(value.optional, stream)
            Proto.Byte.serialize(value.options, stream)
        }

        override fun deserialize(stream: Source): CommandParameter {
            return CommandParameter(
                name = Proto.String.deserialize(stream),
                typeFlags = ProtoLE.UInt.deserialize(stream),
                optional = Proto.Boolean.deserialize(stream),
                options = Proto.Byte.deserialize(stream),
            )
        }
    }
}
