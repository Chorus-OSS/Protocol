package org.chorus_oss.protocol.types.itemstack.request.action

import org.chorus_oss.chorus.item.Item

/**
 * CraftResultsDeprecatedStackRequestAction is an additional, deprecated packet sent by the client after
 * crafting. It holds the final results and the amount of times the recipe was crafted. It shouldn't be used.
 * This action is also sent when an item is enchanted. Enchanting should be treated mostly the same way as
 * crafting, where the old item is consumed.
 */
data class CraftResultsDeprecatedAction(
    val resultItems: Array<Item>,
    val timesCrafted: Int,
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CraftResultsDeprecatedAction

        if (timesCrafted != other.timesCrafted) return false
        if (!resultItems.contentEquals(other.resultItems)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timesCrafted
        result = 31 * result + resultItems.contentHashCode()
        return result
    }
}
