package org.chorus_oss.protocol.types.inventory.transaction

import org.chorus_oss.chorus.item.Item


data class NetworkInventoryAction(
    val inventorySource: InventorySource,
    val inventorySlot: Int,
    val oldItem: Item,
    val newItem: Item,
) {
    fun write(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(inventorySource.type.id())
        when (inventorySource.type) {
            InventorySource.Type.CONTAINER,
            InventorySource.Type.UNTRACKED_INTERACTION_UI,
            InventorySource.Type.NON_IMPLEMENTED_TODO -> byteBuf.writeVarInt(inventorySource.containerId)

            InventorySource.Type.WORLD_INTERACTION -> byteBuf.writeUnsignedVarInt(inventorySource.flag.ordinal)

            else -> Unit
        }
        byteBuf.writeUnsignedVarInt(this.inventorySlot)
        byteBuf.writeSlot(this.oldItem)
        byteBuf.writeSlot(this.newItem)
    }

    companion object {
        const val SOURCE_TYPE_CRAFTING_RESULT: Int = -4
        const val SOURCE_TYPE_CRAFTING_USE_INGREDIENT: Int = -5
        const val SOURCE_TYPE_ANVIL_INPUT: Int = -10
        const val SOURCE_TYPE_ANVIL_MATERIAL: Int = -11
        const val SOURCE_TYPE_ANVIL_RESULT: Int = -12
        const val SOURCE_TYPE_ENCHANT_INPUT: Int = -15
        const val SOURCE_TYPE_ENCHANT_MATERIAL: Int = -16
        const val SOURCE_TYPE_ENCHANT_OUTPUT: Int = -17
        const val SOURCE_TYPE_TRADING_OUTPUT: Int = -30
        const val SOURCE_TYPE_TRADING_INPUT_1: Int = -31
        const val SOURCE_TYPE_TRADING_INPUT_2: Int = -32
        const val SOURCE_TYPE_TRADING_USE_INPUTS: Int = -33

        fun read(byteBuf: ByteBuf): NetworkInventoryAction {
            val type: InventorySource.Type = InventorySource.Type.byId(byteBuf.readUnsignedVarInt())

            val inventorySource = when (type) {
                InventorySource.Type.UNTRACKED_INTERACTION_UI -> InventorySource.fromUntrackedInteractionUI(byteBuf.readVarInt())

                InventorySource.Type.CONTAINER -> InventorySource.fromContainerWindowId(byteBuf.readVarInt())

                InventorySource.Type.GLOBAL -> InventorySource.fromGlobalInventory()
                InventorySource.Type.WORLD_INTERACTION -> {
                    val flag = InventorySource.Flag.entries[byteBuf.readUnsignedVarInt()]
                    InventorySource.fromWorldInteraction(flag)
                }

                InventorySource.Type.CREATIVE -> InventorySource.fromCreativeInventory()
                InventorySource.Type.NON_IMPLEMENTED_TODO -> {
                    val wid = byteBuf.readVarInt()
                    InventorySource.fromNonImplementedTodo(wid)
                }

                else -> InventorySource.fromInvalid()
            }
            val inventorySlot = byteBuf.readUnsignedVarInt()
            val oldItem = byteBuf.readSlot()
            val newItem = byteBuf.readSlot()

            return NetworkInventoryAction(inventorySource, inventorySlot, oldItem, newItem)
        }
    }
}
