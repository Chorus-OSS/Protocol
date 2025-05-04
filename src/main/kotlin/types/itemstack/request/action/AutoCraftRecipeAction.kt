package org.chorus_oss.protocol.types.itemstack.request.action

import org.chorus_oss.chorus.recipe.descriptor.ItemDescriptor

/**
 * AutoCraftRecipeStackRequestActionData is sent by the client similarly to the CraftRecipeStackRequestActionData. The
 * only difference is that the recipe is automatically created and crafted by shift clicking the recipe book.
 */
data class AutoCraftRecipeAction(
    override val recipeNetworkId: Int,
    override val numberOfRequestedCrafts: Int,
    val timesCrafted: Int,
    val ingredients: List<ItemDescriptor>,
) : RecipeItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CRAFT_RECIPE_AUTO
}
