package org.chorus_oss.protocol.types.itemstack.request.action

/**
 * LabTableCombineStackRequestActionData is sent by the client when it uses a lab table to combine item stacks.
 */
class LabTableCombineAction : ItemStackRequestAction {
    override val type: ItemStackRequestActionType
        get() = ItemStackRequestActionType.LAB_TABLE_COMBINE
}
