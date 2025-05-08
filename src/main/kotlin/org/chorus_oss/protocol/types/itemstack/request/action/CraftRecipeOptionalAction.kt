package org.chorus_oss.protocol.types.itemstack.request.action

/**
 * Called when renaming an item in an anvil or cartography table. Uses the filter strings present in the request.
 */
data class CraftRecipeOptionalAction(
    /**
     * For the cartography table, if a certain MULTI recipe is being called, this points to the network ID that was assigned.
     */
    val recipeNetworkId: Int,

    /**
     * Most likely the index in the request's filter strings that this action is using
     */
    val filteredStringIndex: Int,
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL
}
