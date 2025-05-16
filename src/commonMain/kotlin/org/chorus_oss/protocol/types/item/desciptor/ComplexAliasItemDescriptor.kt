package org.chorus_oss.protocol.types.item.desciptor

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class ComplexAliasItemDescriptor(
    val name: String,
) : ItemDescriptor() {
    override val type: ItemDescriptor.Companion.Type
        get() = ItemDescriptor.Companion.Type.ComplexAlias

    companion object : ProtoCodec<ComplexAliasItemDescriptor> {
        override fun serialize(
            value: ComplexAliasItemDescriptor,
            stream: Sink
        ) {
            Proto.String.serialize(value.name, stream)
        }

        override fun deserialize(stream: Source): ComplexAliasItemDescriptor {
            return ComplexAliasItemDescriptor(
                name = Proto.String.deserialize(stream),
            )
        }
    }
}
