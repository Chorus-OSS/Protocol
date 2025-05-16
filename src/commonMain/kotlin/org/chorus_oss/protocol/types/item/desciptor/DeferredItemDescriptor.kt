package org.chorus_oss.protocol.types.item.desciptor

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Short
import org.chorus_oss.protocol.core.types.String

data class DeferredItemDescriptor(
    val name: String,
    val metadataValue: Short,
) : ItemDescriptor() {
    override val type: ItemDescriptor.Companion.Type
        get() = ItemDescriptor.Companion.Type.Deferred

    companion object : ProtoCodec<DeferredItemDescriptor> {
        override fun serialize(
            value: DeferredItemDescriptor,
            stream: Sink
        ) {
            Proto.String.serialize(value.name, stream)
            ProtoLE.Short.serialize(value.metadataValue, stream)
        }

        override fun deserialize(stream: Source): DeferredItemDescriptor {
            return DeferredItemDescriptor(
                name = Proto.String.deserialize(stream),
                metadataValue = ProtoLE.Short.deserialize(stream)
            )
        }
    }
}
