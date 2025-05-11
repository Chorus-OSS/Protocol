package org.chorus_oss.protocol.types.command

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.String

data class ChainedSubcommand(
    val name: String,
    val values: List<ChainedSubcommandValue>
) {
    companion object : ProtoCodec<ChainedSubcommand> {
        override fun serialize(value: ChainedSubcommand, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            ProtoHelper.serializeList(value.values, stream, ChainedSubcommandValue::serialize)
        }

        override fun deserialize(stream: Source): ChainedSubcommand {
            return ChainedSubcommand(
                name = Proto.String.deserialize(stream),
                values = ProtoHelper.deserializeList(stream, ChainedSubcommandValue::deserialize)
            )
        }
    }
}
