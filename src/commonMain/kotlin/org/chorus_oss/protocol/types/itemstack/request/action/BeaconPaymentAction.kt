package org.chorus_oss.protocol.types.itemstack.request.action

/**
 * BeaconPaymentStackRequestActionData is sent by the client when it submits an item to enable effects from a
 * beacon. These items will have been moved into the beacon item slot in advance.
 */
data class BeaconPaymentAction(
    val primaryEffect: Int,
    val secondaryEffect: Int,
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.BEACON_PAYMENT
}
