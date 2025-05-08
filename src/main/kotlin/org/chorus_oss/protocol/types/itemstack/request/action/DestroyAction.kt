package org.chorus_oss.protocol.types.itemstack.request.action

import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

/**
 * DestroyStackRequestActionData is sent by the client when it destroys an item in creative mode by moving it
 * back into the creative inventory.
 */
data class DestroyAction(
    val count: Int,
    val source: ItemStackRequestSlotData,
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.DESTROY
}
