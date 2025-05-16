package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

data class TakeFromItemContainerRequestAction(
    val count: Byte,
    val source: ItemStackRequestSlotData,
    val destination: ItemStackRequestSlotData,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.PLACE

    companion object : ProtoCodec<TakeFromItemContainerRequestAction> {
        override fun serialize(
            value: TakeFromItemContainerRequestAction,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.count, stream)
            ItemStackRequestSlotData.serialize(value.source, stream)
            ItemStackRequestSlotData.serialize(value.destination, stream)
        }

        override fun deserialize(stream: Source): TakeFromItemContainerRequestAction {
            return TakeFromItemContainerRequestAction(
                count = Proto.Byte.deserialize(stream),
                source = ItemStackRequestSlotData.deserialize(stream),
                destination = ItemStackRequestSlotData.deserialize(stream)
            )
        }
    }
}
