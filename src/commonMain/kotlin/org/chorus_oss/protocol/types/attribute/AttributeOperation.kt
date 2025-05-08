package org.chorus_oss.protocol.types.attribute

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

enum class AttributeOperation {
    ADDITION,
    MULTIPLY_BASE,
    MULTIPLY_TOTAL,
    CAP,
    INVALID;

    companion object : ProtoCodec<AttributeOperation> {
        override fun serialize(value: AttributeOperation, stream: Buffer) {
            ProtoLE.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Buffer): AttributeOperation {
            return entries[ProtoLE.Int.deserialize(stream)]
        }
    }
}