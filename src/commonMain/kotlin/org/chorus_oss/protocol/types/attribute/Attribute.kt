package org.chorus_oss.protocol.types.attribute

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String

data class Attribute(
    val name: String,
    val value: Float,
    val min: Float,
    val max: Float,
    val defaultMin: Float,
    val defaultMax: Float,
    val modifiers: List<AttributeModifier>
) {
    companion object : ProtoCodec<Attribute> {
        override fun serialize(value: Attribute, stream: Buffer) {
            ProtoLE.Float.serialize(value.min, stream)
            ProtoLE.Float.serialize(value.max, stream)
            ProtoLE.Float.serialize(value.value, stream)
            ProtoLE.Float.serialize(value.defaultMin, stream)
            ProtoLE.Float.serialize(value.defaultMax, stream)
            Proto.String.serialize(value.name, stream)
            ProtoHelper.serializeList(value.modifiers, stream, AttributeModifier::serialize)
        }

        override fun deserialize(stream: Buffer): Attribute {
            return Attribute(
                min = ProtoLE.Float.deserialize(stream),
                max = ProtoLE.Float.deserialize(stream),
                value = ProtoLE.Float.deserialize(stream),
                defaultMin = ProtoLE.Float.deserialize(stream),
                defaultMax = ProtoLE.Float.deserialize(stream),
                name = Proto.String.deserialize(stream),
                modifiers = ProtoHelper.deserializeList(stream, AttributeModifier::deserialize)
            )
        }
    }
}
