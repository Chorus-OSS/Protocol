package org.chorus_oss.protocol.types.itemstack.request.action

import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

/**
 * TakeStackRequestActionData is sent by the client to the server to take x amount of items from one slot in a
 * container to the cursor.
 */
class TakeAction(
    override val count: Int,
    override val source: ItemStackRequestSlotData,
    override val destination: ItemStackRequestSlotData,
) : TransferItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.TAKE
}
