package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

data class SwapRequestAction(
    val source: ItemStackRequestSlotData,
    val destination: ItemStackRequestSlotData,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.Swap

    companion object : ProtoCodec<SwapRequestAction> {
        override fun serialize(
            value: SwapRequestAction,
            stream: Sink
        ) {
            ItemStackRequestSlotData.serialize(value.source, stream)
            ItemStackRequestSlotData.serialize(value.destination, stream)
        }

        override fun deserialize(stream: Source): SwapRequestAction {
            return SwapRequestAction(
                source = ItemStackRequestSlotData.deserialize(stream),
                destination = ItemStackRequestSlotData.deserialize(stream)
            )
        }
    }
}
