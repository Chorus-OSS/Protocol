package org.chorus_oss.protocol.types.inventory.transaction

import org.chorus_oss.chorus.item.Item
import org.chorus_oss.chorus.math.Vector3

data class ReleaseItemData(
    val actionType: Int,
    val hotbarSlot: Int,
    val itemInHand: Item,
    val headRot: Vector3,
) : TransactionData
