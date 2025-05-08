package org.chorus_oss.protocol.types.itemstack.request.action

data class CraftGrindstoneAction(
    override val recipeNetworkId: Int,
    override val numberOfRequestedCrafts: Int,
    val repairCost: Int,
) : RecipeItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT
}