package org.chorus_oss.protocol.types.item.desciptor

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class ItemDescriptorCount(
    val descriptor: ItemDescriptor,
    val count: Int
) {
    companion object : ProtoCodec<ItemDescriptorCount> {
        override fun serialize(
            value: ItemDescriptorCount,
            stream: Sink
        ) {
            ItemDescriptor.serialize(value.descriptor, stream)
            ProtoVAR.Int.serialize(value.count, stream)
        }

        override fun deserialize(stream: Source): ItemDescriptorCount {
            return ItemDescriptorCount(
                descriptor = ItemDescriptor.deserialize(stream),
                count = ProtoVAR.Int.deserialize(stream)
            )
        }
    }
}
