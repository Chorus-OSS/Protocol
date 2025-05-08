package org.chorus_oss.protocol.types.itemstack.request.action

import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

/**
 * PlaceStackRequestAction is sent by the client to the server to place x amount of items from one slot into
 * another slot, such as when shift clicking an item in the inventory to move it around or when moving an item
 * in the cursor into a slot.
 */
data class PlaceAction(
    override val count: Int,
    override val source: ItemStackRequestSlotData,
    override val destination: ItemStackRequestSlotData,
) : TransferItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.PLACE
}
