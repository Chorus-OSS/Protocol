package org.chorus_oss.protocol.types.attribute

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String

data class AttributeValue(
    val name: String,
    val value: Float,
    val min: Float,
    val max: Float,
) {
    companion object : ProtoCodec<AttributeValue> {
        override fun serialize(value: AttributeValue, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            ProtoLE.Float.serialize(value.min, stream)
            ProtoLE.Float.serialize(value.value, stream)
            ProtoLE.Float.serialize(value.max, stream)
        }

        override fun deserialize(stream: Source): AttributeValue {
            return AttributeValue(
                name = Proto.String.deserialize(stream),
                min = ProtoLE.Float.deserialize(stream),
                value = ProtoLE.Float.deserialize(stream),
                max = ProtoLE.Float.deserialize(stream),
            )
        }
    }
}
