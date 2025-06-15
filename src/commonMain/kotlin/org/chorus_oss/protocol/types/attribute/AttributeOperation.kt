package org.chorus_oss.protocol.types.attribute

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

enum class AttributeOperation {
    Addition,
    MultiplyBase,
    MultiplyTotal,
    Cap,
    Invalid;

    companion object : ProtoCodec<AttributeOperation> {
        override fun serialize(value: AttributeOperation, stream: Sink) {
            ProtoLE.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Source): AttributeOperation {
            return entries[ProtoLE.Int.deserialize(stream)]
        }
    }
}