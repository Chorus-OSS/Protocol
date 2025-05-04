package org.chorus_oss.protocol.types.itemstack.request.action

data class CraftLoomAction(
    val patternId: String
) : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CRAFT_LOOM
}