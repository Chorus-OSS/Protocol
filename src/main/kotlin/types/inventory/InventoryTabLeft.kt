package org.chorus_oss.protocol.types.inventory

enum class InventoryTabLeft {
    NONE,
    RECIPE_CONSTRUCTION,
    RECIPE_EQUIPMENT,
    RECIPE_ITEMS,
    RECIPE_NATURE,
    RECIPE_SEARCH,
    SURVIVAL;

    companion object {
        val VALUES: Array<InventoryTabLeft> = entries.toTypedArray()
    }
}
