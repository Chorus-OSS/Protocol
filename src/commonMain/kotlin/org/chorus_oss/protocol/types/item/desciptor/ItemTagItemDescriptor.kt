package org.chorus_oss.protocol.types.item.desciptor

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class ItemTagItemDescriptor(
    val tag: String,
) : ItemDescriptor() {
    override val type: ItemDescriptor.Companion.Type
        get() = ItemDescriptor.Companion.Type.ItemTag

    companion object : ProtoCodec<ItemTagItemDescriptor> {
        override fun serialize(
            value: ItemTagItemDescriptor,
            stream: Sink
        ) {
            Proto.String.serialize(value.tag, stream)
        }

        override fun deserialize(stream: Source): ItemTagItemDescriptor {
            return ItemTagItemDescriptor(
                tag = Proto.String.deserialize(stream),
            )
        }
    }
}
