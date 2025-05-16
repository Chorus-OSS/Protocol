package org.chorus_oss.protocol.types.inventory.transaction

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.shared.types.Vector3f
import org.chorus_oss.protocol.types.item.ItemStack

data class ReleaseItemTransactionData(
    val actionType: ActionType,
    val hotbarSlot: Int,
    val itemInHand: ItemStack,
    val headRotation: Vector3f,
) : InventoryTransactionData {
    companion object : ProtoCodec<ReleaseItemTransactionData> {
        enum class ActionType {
            RELEASE,
            CONSUME;

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
            value: ReleaseItemTransactionData,
            stream: Sink
        ) {
            ActionType.serialize(value.actionType, stream)
            ProtoVAR.Int.serialize(value.hotbarSlot, stream)
            ItemStack.serialize(value.itemInHand, stream)
            Vector3f.serialize(value.headRotation, stream)
        }

        override fun deserialize(stream: Source): ReleaseItemTransactionData {
            return ReleaseItemTransactionData(
                actionType = ActionType.deserialize(stream),
                hotbarSlot = ProtoVAR.Int.deserialize(stream),
                itemInHand = ItemStack.deserialize(stream),
                headRotation = Vector3f.deserialize(stream),
            )
        }
    }
}
