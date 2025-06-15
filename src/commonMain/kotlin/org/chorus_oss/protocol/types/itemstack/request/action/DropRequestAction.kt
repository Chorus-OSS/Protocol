package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

data class DropRequestAction(
    val count: Byte,
    val source: ItemStackRequestSlotData,
    val randomly: Boolean,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.Drop

    companion object : ProtoCodec<DropRequestAction> {
        override fun serialize(
            value: DropRequestAction,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.count, stream)
            ItemStackRequestSlotData.serialize(value.source, stream)
            Proto.Boolean.serialize(value.randomly, stream)
        }

        override fun deserialize(stream: Source): DropRequestAction {
            return DropRequestAction(
                count = Proto.Byte.deserialize(stream),
                source = ItemStackRequestSlotData.deserialize(stream),
                randomly = Proto.Boolean.deserialize(stream)
            )
        }
    }
}
