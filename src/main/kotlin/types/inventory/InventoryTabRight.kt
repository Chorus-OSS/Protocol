package org.chorus_oss.protocol.types.inventory

enum class InventoryTabRight {
    NONE,
    FULL_SCREEN,
    CRAFTING,
    ARMOR;

    companion object {
        val VALUES: Array<InventoryTabRight> = entries.toTypedArray()
    }
}
