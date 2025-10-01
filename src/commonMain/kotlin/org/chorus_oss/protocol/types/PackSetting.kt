package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt

data class PackSetting(
    val name: String,
    val value: Value,
) {
    sealed class Value {
        companion object : ProtoCodec<Value> {
            override fun serialize(value: Value, stream: Sink) {
                when (value) {
                    is FloatValue -> {
                        ProtoVAR.UInt.serialize(0u, stream)
                        ProtoLE.Float.serialize(value.value, stream)
                    }
                    is BooleanValue -> {
                        ProtoVAR.UInt.serialize(1u, stream)
                        Proto.Boolean.serialize(value.value, stream)
                    }
                    is StringValue -> {
                        ProtoVAR.UInt.serialize(2u, stream)
                        Proto.String.serialize(value.value, stream)
                    }
                }
            }

            override fun deserialize(stream: Source): Value {
                return when (val type: UInt = ProtoVAR.UInt.deserialize(stream)) {
                    0u -> FloatValue(
                        value = ProtoLE.Float.deserialize(stream),
                    )
                    1u -> BooleanValue(
                        value = Proto.Boolean.deserialize(stream),
                    )
                    2u -> StringValue(
                        value = Proto.String.deserialize(stream),
                    )
                    else -> throw IllegalStateException("Invalid value type: $type")
                }
            }
        }
    }

    data class FloatValue(val value: Float) : Value()
    data class BooleanValue(val value: Boolean) : Value()
    data class StringValue(val value: String) : Value()

    companion object : ProtoCodec<PackSetting> {
        override fun serialize(value: PackSetting, stream: Sink) {
            Proto.String.serialize(value.name, stream)
            Value.serialize(value.value, stream)
        }

        override fun deserialize(stream: Source): PackSetting {
            return PackSetting(
                name = Proto.String.deserialize(stream),
                value = Value.deserialize(stream),
            )
        }
    }
}
