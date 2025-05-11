package org.chorus_oss.protocol.types.command

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.UShort

data class ChainedSubcommandValue(
    val index: UShort,
    val value: UShort,
) {
    companion object : ProtoCodec<ChainedSubcommandValue> {
        override fun serialize(value: ChainedSubcommandValue, stream: Sink) {
            ProtoLE.UShort.serialize(value.index, stream)
            ProtoLE.UShort.serialize(value.value, stream)
        }

        override fun deserialize(stream: Source): ChainedSubcommandValue {
            return ChainedSubcommandValue(
                index = ProtoLE.UShort.deserialize(stream),
                value = ProtoLE.UShort.deserialize(stream)
            )
        }
    }
}
