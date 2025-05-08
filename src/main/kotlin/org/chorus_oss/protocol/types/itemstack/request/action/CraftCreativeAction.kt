package org.chorus_oss.protocol.types.itemstack.request.action

/**
 * CraftCreativeStackRequestActionData is sent by the client when it takes an item out of the creative inventory.
 * The item is thus not really crafted, but instantly created.
 */
data class CraftCreativeAction(
    /**
     * creativeItemNetworkId is the network ID of the creative item that is being created. This is one of the
     * creative item network IDs sent in the CreativeContent packet.
     */
    val creativeItemNetworkId: Int,
    val numberOfRequestedCrafts: Int,
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CRAFT_CREATIVE
}
