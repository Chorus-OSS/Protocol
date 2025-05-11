package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Float
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt

data class ActorProperties(
    val intProperties: List<IntProperty>,
    val floatProperties: List<FloatProperty>
) {
    companion object : ProtoCodec<ActorProperties> {
        data class FloatProperty(
            val index: UInt,
            val value: Float
        ) {
            companion object : ProtoCodec<FloatProperty> {
                override fun serialize(value: FloatProperty, stream: Sink) {
                    ProtoVAR.UInt.serialize(value.index, stream)
                    ProtoLE.Float.serialize(value.value, stream)
                }

                override fun deserialize(stream: Source): FloatProperty {
                    return FloatProperty(
                        index = ProtoVAR.UInt.deserialize(stream),
                        value = ProtoLE.Float.deserialize(stream)
                    )
                }
            }
        }

        data class IntProperty(
            val index: UInt,
            val value: Int
        ) {
            companion object : ProtoCodec<IntProperty> {
                override fun serialize(value: IntProperty, stream: Sink) {
                    ProtoVAR.UInt.serialize(value.index, stream)
                    ProtoVAR.Int.serialize(value.value, stream)
                }

                override fun deserialize(stream: Source): IntProperty {
                    return IntProperty(
                        index = ProtoVAR.UInt.deserialize(stream),
                        value = ProtoVAR.Int.deserialize(stream)
                    )
                }
            }
        }

        override fun serialize(value: ActorProperties, stream: Sink) {
            ProtoHelper.serializeList(value.intProperties, stream, IntProperty::serialize)
            ProtoHelper.serializeList(value.floatProperties, stream, FloatProperty::serialize)
        }

        override fun deserialize(stream: Source): ActorProperties {
            return ActorProperties(
                intProperties = ProtoHelper.deserializeList(stream, IntProperty::deserialize),
                floatProperties = ProtoHelper.deserializeList(stream, FloatProperty::deserialize)
            )
        }
    }
}
