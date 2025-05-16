package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

data class CreateRequestAction(
    val resultsSlot: Byte,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CREATE

    companion object : ProtoCodec<CreateRequestAction> {
        override fun serialize(
            value: CreateRequestAction,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.resultsSlot, stream)
        }

        override fun deserialize(stream: Source): CreateRequestAction {
            return CreateRequestAction(
                resultsSlot = Proto.Byte.deserialize(stream),
            )
        }
    }
}
