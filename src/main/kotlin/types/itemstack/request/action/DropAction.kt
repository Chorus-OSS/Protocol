package org.chorus_oss.protocol.types.itemstack.request.action

import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequestSlotData

/**
 * DropStackRequestActionData is sent by the client when it drops an item out of the inventory when it has its
 * inventory opened. This action is not sent when a player drops an item out of the hotbar using the Q button
 * (or the equivalent on mobile). The InventoryTransaction packet is still used for that action, regardless of
 * whether the item stack network IDs are used or not.
 */
data class DropAction(
    val count: Int,
    val source: ItemStackRequestSlotData,
    val randomly: Boolean, // ?? Perhaps deals with order of items being dropped? Normally false.
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.DROP
}
