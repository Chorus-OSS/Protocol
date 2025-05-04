package org.chorus_oss.protocol.types.itemstack.request.action

/**
 * CraftRecipeStackRequestActionData is sent by the client the moment it begins crafting an item. This is the
 * first action sent, before the Consume and Create item stack request actions.
 * This action is also sent when an item is enchanted. Enchanting should be treated mostly the same way as
 * crafting, where the old item is consumed.
 */
data class CraftRecipeAction(
    override val recipeNetworkId: Int,
    override val numberOfRequestedCrafts: Int,
) : RecipeItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CRAFT_RECIPE
}
