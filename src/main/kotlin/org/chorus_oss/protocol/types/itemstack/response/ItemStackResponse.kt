package org.chorus_oss.protocol.types.itemstack.response

import org.chorus_oss.protocol.packets.ItemStackResponsePacket
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequest

/**
 * Represents an individual response to a [ItemStackRequest]
 * sent as part of [ItemStackResponsePacket].
 */
data class ItemStackResponse(
    /**
     * Replaces the success boolean as of v419
     */
    var result: ItemStackResponseStatus,
    /**
     * requestId is the unique ID of the request that this response is in reaction to. If rejected, the client
     * will undo the actions from the request with this ID.
     */
    val requestId: Int,
    /**
     * containers holds information on the containers that had their contents changed as a result of the
     * request.
     */
    val containers: MutableList<ItemStackResponseContainer>,
)
