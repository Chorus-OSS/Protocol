package org.chorus_oss.protocol.types.inventory

enum class InventoryLayout {
    NONE,
    SURVIVAL,
    RECIPE_BOOK,
    CREATIVE;

    companion object {
        val VALUES: Array<InventoryLayout> = entries.toTypedArray()
    }
}
