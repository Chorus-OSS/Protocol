package org.chorus_oss.protocol.types.item.desciptor

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec

class InvalidItemDescriptor : ItemDescriptor() {
    override val type: Companion.Type
        get() = ItemDescriptor.Companion.Type.Invalid

    companion object : ProtoCodec<InvalidItemDescriptor> {
        override fun serialize(
            value: InvalidItemDescriptor,
            stream: Sink
        ) = Unit

        override fun deserialize(stream: Source): InvalidItemDescriptor = InvalidItemDescriptor()
    }
}