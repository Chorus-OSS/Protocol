package org.chorus_oss.protocol.types.itemstack.request.action

/**
 * CraftNonImplementedStackRequestActionData is an action sent for inventory actions that aren't yet implemented
 * in the new system. These include, for example, anvils
 */
class CraftNonImplementedAction : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.CRAFT_NON_IMPLEMENTED_DEPRECATED
}
