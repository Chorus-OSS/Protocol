package org.chorus_oss.protocol.types.itemstack.request.action

import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

/**
 * ConsumeStackRequestAction is sent by the client when it uses an item to craft another item. The original
 * item is 'consumed'.
 */
data class ConsumeAction(
    val count: Int,
    val source: ItemStackRequestSlotData,
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CONSUME
}
