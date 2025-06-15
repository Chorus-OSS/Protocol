package org.chorus_oss.protocol.types.inventory.transaction

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.IVector3
import org.chorus_oss.protocol.types.UIVector3
import org.chorus_oss.protocol.types.Vector3f
import org.chorus_oss.protocol.types.item.ItemStack

data class UseItemTransactionData(
    val actionType: ActionType,
    val triggerType: TriggerType,
    val blockPosition: IVector3,
    val blockFace: Int,
    val hotbarSlot: Int,
    val itemInHand: ItemStack,
    val position: Vector3f,
    val clickPosition: Vector3f,
    val blockRuntimeID: UInt,
    val clientPrediction: PredictedResult,
) : InventoryTransactionData {
    companion object : ProtoCodec<UseItemTransactionData> {
        enum class ActionType {
            ClickBlock,
            ClickAir,
            BreakBlock;

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

        enum class TriggerType {
            Unknown,
            PlayerInput,
            SimulationTick;

            companion object : ProtoCodec<TriggerType> {
                override fun serialize(
                    value: TriggerType,
                    stream: Sink
                ) {
                    ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): TriggerType {
                    return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
                }
            }
        }

        enum class PredictedResult {
            Failure,
            Success;

            companion object : ProtoCodec<PredictedResult> {
                override fun serialize(
                    value: PredictedResult,
                    stream: Sink
                ) {
                    ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): PredictedResult {
                    return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
                }
            }

        }

        override fun serialize(
            value: UseItemTransactionData,
            stream: Sink
        ) {
            ActionType.serialize(value.actionType, stream)
            TriggerType.serialize(value.triggerType, stream)
            UIVector3.serialize(value.blockPosition, stream)
            ProtoVAR.Int.serialize(value.blockFace, stream)
            ProtoVAR.Int.serialize(value.hotbarSlot, stream)
            ItemStack.serialize(value.itemInHand, stream)
            Vector3f.serialize(value.position, stream)
            Vector3f.serialize(value.clickPosition, stream)
            ProtoVAR.UInt.serialize(value.blockRuntimeID, stream)
            PredictedResult.serialize(value.clientPrediction, stream)
        }

        override fun deserialize(stream: Source): UseItemTransactionData {
            return UseItemTransactionData(
                actionType = ActionType.deserialize(stream),
                triggerType = TriggerType.deserialize(stream),
                blockPosition = UIVector3.deserialize(stream),
                blockFace = ProtoVAR.Int.deserialize(stream),
                hotbarSlot = ProtoVAR.Int.deserialize(stream),
                itemInHand = ItemStack.deserialize(stream),
                position = Vector3f.deserialize(stream),
                clickPosition = Vector3f.deserialize(stream),
                blockRuntimeID = ProtoVAR.UInt.deserialize(stream),
                clientPrediction = PredictedResult.deserialize(stream),
            )
        }
    }
}
