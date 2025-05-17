package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

data class EnchantmentInstance(
    val type: Byte,
    val level: Byte,
) {
    companion object : ProtoCodec<EnchantmentInstance> {
        override fun serialize(value: EnchantmentInstance, stream: Sink) {
            Proto.Byte.serialize(value.type, stream)
            Proto.Byte.serialize(value.level, stream)
        }

        override fun deserialize(stream: Source): EnchantmentInstance {
            return EnchantmentInstance(
                type = Proto.Byte.deserialize(stream),
                level = Proto.Byte.deserialize(stream),
            )
        }
    }
}
