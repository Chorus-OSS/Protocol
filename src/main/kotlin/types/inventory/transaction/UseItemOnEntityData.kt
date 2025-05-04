package org.chorus_oss.protocol.types.inventory.transaction

import org.chorus_oss.chorus.item.Item
import org.chorus_oss.chorus.math.Vector3

data class UseItemOnEntityData(
    val entityRuntimeId: Long,
    val actionType: Int,
    val hotbarSlot: Int,
    val itemInHand: Item,
    val playerPos: Vector3,
    val clickPos: Vector3,
) : TransactionData
