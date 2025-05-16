package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

data class DestroyRequestAction(
    val count: Byte,
    val source: ItemStackRequestSlotData,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.DESTROY

    companion object : ProtoCodec<DestroyRequestAction> {
        override fun serialize(
            value: DestroyRequestAction,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.count, stream)
            ItemStackRequestSlotData.serialize(value.source, stream)
        }

        override fun deserialize(stream: Source): DestroyRequestAction {
            return DestroyRequestAction(
                count = Proto.Byte.deserialize(stream),
                source = ItemStackRequestSlotData.deserialize(stream)
            )
        }
    }
}
