package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

class TakeRequestAction(
    val count: Byte,
    val source: ItemStackRequestSlotData,
    val destination: ItemStackRequestSlotData,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.TAKE

    companion object : ProtoCodec<TakeRequestAction> {
        override fun serialize(
            value: TakeRequestAction,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.count, stream)
            ItemStackRequestSlotData.serialize(value.source, stream)
            ItemStackRequestSlotData.serialize(value.destination, stream)
        }

        override fun deserialize(stream: Source): TakeRequestAction {
            return TakeRequestAction(
                count = Proto.Byte.deserialize(stream),
                source = ItemStackRequestSlotData.deserialize(stream),
                destination = ItemStackRequestSlotData.deserialize(stream)
            )
        }
    }
}
