package org.chorus_oss.protocol.types.itemstack.request

import org.chorus_oss.protocol.packets.ItemStackRequestPacket
import org.chorus_oss.protocol.packets.PlayerAuthInputPacket
import org.chorus_oss.protocol.types.itemstack.request.action.ItemStackRequestAction

/**
 * Request represents a single request present in an [ItemStackRequestPacket] or
 * [PlayerAuthInputPacket] sent by the client to
 * change an item in an inventory.
 * Item stack requests are either approved or rejected by the server using the ItemStackResponse packet.
 */
data class ItemStackRequest(
    /**
     * requestId is a unique ID for the request. This ID is used by the server to send a response for this
     * specific request in the ItemStackResponse packet.
     */
    var requestId: Int,

    /**
     * actions is a list of actions performed by the client. The actual type of the actions depends on which
     * ID was present
     */
    var actions: Array<ItemStackRequestAction>,

    /**
     * Used for the server to determine which strings should be filtered. Used in anvils to verify a renamed item.
     *
     * @since v422
     */
    var filterStrings: Array<String>,

    /**
     * @since v552
     */
    var textProcessingEventOrigin: TextProcessingEventOrigin?
) {
    constructor(
        requestId: Int,
        actions: Array<ItemStackRequestAction>,
        filterStrings: Array<String>
    ) : this(requestId, actions, filterStrings, TextProcessingEventOrigin.BLOCK_ENTITY_DATA_TEXT)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemStackRequest

        if (requestId != other.requestId) return false
        if (!actions.contentEquals(other.actions)) return false
        if (!filterStrings.contentEquals(other.filterStrings)) return false
        if (textProcessingEventOrigin != other.textProcessingEventOrigin) return false

        return true
    }

    override fun hashCode(): Int {
        var result = requestId
        result = 31 * result + actions.contentHashCode()
        result = 31 * result + filterStrings.contentHashCode()
        result = 31 * result + (textProcessingEventOrigin?.hashCode() ?: 0)
        return result
    }
}
