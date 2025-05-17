package org.chorus_oss.protocol.types.itemstack.request

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.itemstack.request.action.ItemStackRequestAction

data class ItemStackRequest(
    var requestId: Int,
    var actions: List<ItemStackRequestAction>,
    var filterStrings: List<String>,
    var textProcessingEventOrigin: FilterCause
) {
    companion object : ProtoCodec<ItemStackRequest> {
        override fun serialize(
            value: ItemStackRequest,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.requestId, stream)
            ProtoHelper.serializeList(value.actions, stream, ItemStackRequestAction)
            ProtoHelper.serializeList(value.filterStrings, stream, Proto.String)
            FilterCause.serialize(value.textProcessingEventOrigin, stream)
        }

        override fun deserialize(stream: Source): ItemStackRequest {
            return ItemStackRequest(
                requestId = ProtoVAR.Int.deserialize(stream),
                actions = ProtoHelper.deserializeList(stream, ItemStackRequestAction),
                filterStrings = ProtoHelper.deserializeList(stream, Proto.String),
                textProcessingEventOrigin = FilterCause.deserialize(stream)
            )
        }
    }
}
