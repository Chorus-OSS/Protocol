package org.chorus_oss.protocol.types.inventory.transaction

import org.chorus_oss.chorus.item.Item
import org.chorus_oss.chorus.math.BlockFace
import org.chorus_oss.chorus.math.BlockVector3
import org.chorus_oss.chorus.math.Vector3
import org.chorus_oss.chorus.math.Vector3f

data class UseItemData(
    val actionType: Int,
    val blockPos: BlockVector3,
    val face: BlockFace,
    val hotbarSlot: Int,
    val itemInHand: Item,
    val playerPos: Vector3,
    val clickPos: Vector3f,
    val blockRuntimeId: Int,
    val clientInteractPrediction: PredictedResult,
    val triggerType: TriggerType,
) : TransactionData {
    enum class PredictedResult {
        FAILURE,
        SUCCESS
    }

    enum class TriggerType {
        UNKNOWN,
        PLAYER_INPUT,
        SIMULATION_TICK
    }
}
