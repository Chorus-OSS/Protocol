package org.chorus_oss.protocol.types.item.desciptor

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String

data class MoLangItemDescriptor(
    val expression: String,
    val version: Byte
) : ItemDescriptor() {
    override val type: ItemDescriptor.Companion.Type
        get() = ItemDescriptor.Companion.Type.MoLang

    companion object : ProtoCodec<MoLangItemDescriptor> {
        override fun serialize(
            value: MoLangItemDescriptor,
            stream: Sink
        ) {
            Proto.String.serialize(value.expression, stream)
            Proto.Byte.serialize(value.version, stream)
        }

        override fun deserialize(stream: Source): MoLangItemDescriptor {
            return MoLangItemDescriptor(
                expression = Proto.String.deserialize(stream),
                version = Proto.Byte.deserialize(stream)
            )
        }
    }
}
