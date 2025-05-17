package org.chorus_oss.protocol.types.inventory.transaction

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.Vector3f
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.item.ItemStack

data class UseItemOnEntityTransactionData(
    val entityRuntimeID: ActorRuntimeID,
    val actionType: ActionType,
    val hotbarSlot: Int,
    val itemInHand: ItemStack,
    val playerPos: Vector3f,
    val clickPos: Vector3f,
) : InventoryTransactionData {
    companion object : ProtoCodec<UseItemOnEntityTransactionData> {
        enum class ActionType {
            INTERACT,
            ATTACK;

            companion object : ProtoCodec<ActionType> {
                override fun serialize(
                    value: ActionType,
                    stream: Sink
                ) {
                    ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): ActionType {
                    return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(
            value: UseItemOnEntityTransactionData,
            stream: Sink
        ) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            ActionType.serialize(value.actionType, stream)
            ProtoVAR.Int.serialize(value.hotbarSlot, stream)
            ItemStack.serialize(value.itemInHand, stream)
            Vector3f.serialize(value.playerPos, stream)
            Vector3f.serialize(value.clickPos, stream)
        }

        override fun deserialize(stream: Source): UseItemOnEntityTransactionData {
            return UseItemOnEntityTransactionData(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                actionType = ActionType.deserialize(stream),
                hotbarSlot = ProtoVAR.Int.deserialize(stream),
                itemInHand = ItemStack.deserialize(stream),
                playerPos = Vector3f.deserialize(stream),
                clickPos = Vector3f.deserialize(stream),
            )
        }
    }
}
