package org.chorus_oss.protocol.types.itemstack.request.action

import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData


/**
 * SwapStackRequestActionData is sent by the client to swap the item in its cursor with an item present in another
 * container. The two item stacks swap places.
 */

data class SwapAction(
    val source: ItemStackRequestSlotData,
    val destination: ItemStackRequestSlotData,
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.SWAP
}
