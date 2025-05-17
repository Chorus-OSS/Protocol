package org.chorus_oss.protocol.types.itemstack.response

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.inventory.FullContainerName

data class ItemStackResponseContainer(
    val container: FullContainerName,
    val slots: List<ItemStackResponseSlot>,
) {
    companion object : ProtoCodec<ItemStackResponseContainer> {
        override fun serialize(
            value: ItemStackResponseContainer,
            stream: Sink
        ) {
            FullContainerName.serialize(value.container, stream)
            ProtoHelper.serializeList(value.slots, stream, ItemStackResponseSlot)
        }

        override fun deserialize(stream: Source): ItemStackResponseContainer {
            return ItemStackResponseContainer(
                container = FullContainerName.deserialize(stream),
                slots = ProtoHelper.deserializeList(stream, ItemStackResponseSlot)
            )
        }
    }
}
