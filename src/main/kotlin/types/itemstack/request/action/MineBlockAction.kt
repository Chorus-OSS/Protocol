package org.chorus_oss.protocol.types.itemstack.request.action

/**
 * MineBlockStackRequestActionData is sent by the client when it breaks a block.
 */
data class MineBlockAction(
    val hotbarSlot: Int,
    val predictedDurability: Int,
    val stackNetworkId: Int,
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.MINE_BLOCK
}
