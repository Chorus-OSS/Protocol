package org.chorus_oss.protocol.types.attribute

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String

data class AttributeModifier(
    val id: String,
    val name: String,
    val amount: Float,
    val operation: AttributeOperation,
    val operand: Int,
    val serializable: Boolean,
) {
    companion object : ProtoCodec<AttributeModifier> {
        override fun serialize(value: AttributeModifier, stream: Buffer) {
            Proto.String.serialize(value.id, stream)
            Proto.String.serialize(value.name, stream)
            ProtoLE.Float.serialize(value.amount, stream)
            AttributeOperation.serialize(value.operation, stream)
            ProtoLE.Int.serialize(value.operand, stream)
            Proto.Boolean.serialize(value.serializable, stream)
        }

        override fun deserialize(stream: Buffer): AttributeModifier {
            return AttributeModifier(
                id = Proto.String.deserialize(stream),
                name = Proto.String.deserialize(stream),
                amount = ProtoLE.Float.deserialize(stream),
                operation = AttributeOperation.deserialize(stream),
                operand = ProtoLE.Int.deserialize(stream),
                serializable = Proto.Boolean.deserialize(stream)
            )
        }
    }
}
