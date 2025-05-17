package org.chorus_oss.protocol.types.itemstack.response

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class ItemStackResponse(
    val result: ItemStackResponseStatus,
    val requestID: Int,
    val containers: List<ItemStackResponseContainer>,
) {
    companion object : ProtoCodec<ItemStackResponse> {
        override fun serialize(
            value: ItemStackResponse,
            stream: Sink
        ) {
            ItemStackResponseStatus.serialize(value.result, stream)
            ProtoVAR.Int.serialize(value.requestID, stream)
            when (value.result) {
                ItemStackResponseStatus.OK -> ProtoHelper.serializeList(value.containers, stream, ItemStackResponseContainer)
                else -> Unit
            }
        }

        override fun deserialize(stream: Source): ItemStackResponse {
            val result: ItemStackResponseStatus
            return ItemStackResponse(
                result = ItemStackResponseStatus.deserialize(stream).also { result = it },
                requestID = ProtoVAR.Int.deserialize(stream),
                containers = when (result) {
                    ItemStackResponseStatus.OK -> ProtoHelper.deserializeList(stream, ItemStackResponseContainer)
                    else -> emptyList()
                }
            )
        }

    }
}
