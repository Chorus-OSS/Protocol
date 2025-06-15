package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.LegacySetItemSlotData
import org.chorus_oss.protocol.types.inventory.transaction.*

data class InventoryTransactionPacket(
    val legacyRequestID: Int,
    val legacySetItemSlots: List<LegacySetItemSlotData>?,
    val transactionType: TransactionType,
    val actions: List<InventoryAction>,
    val transactionData: InventoryTransactionData?,
) : Packet(id) {
    companion object : PacketCodec<InventoryTransactionPacket> {
        enum class TransactionType {
            Normal,
            Mismatch,
            UseItem,
            UseItemOnEntity,
            ReleaseItem;

            companion object : ProtoCodec<TransactionType> {
                override fun serialize(
                    value: TransactionType,
                    stream: Sink
                ) {
                    ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): TransactionType {
                    return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int = 30

        override fun serialize(
            value: InventoryTransactionPacket,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.legacyRequestID, stream)
            when (value.legacyRequestID != 0) {
                true -> (value.legacySetItemSlots as List<LegacySetItemSlotData>).let {
                    ProtoHelper.serializeList(it, stream, LegacySetItemSlotData)
                }

                false -> Unit
            }
            TransactionType.serialize(value.transactionType, stream)
            ProtoHelper.serializeList(value.actions, stream, InventoryAction)
            when (value.transactionType) {
                TransactionType.UseItem -> (value.transactionData as UseItemTransactionData).let {
                    UseItemTransactionData.serialize(
                        it,
                        stream
                    )
                }

                TransactionType.UseItemOnEntity -> (value.transactionData as UseItemOnEntityTransactionData).let {
                    UseItemOnEntityTransactionData.serialize(
                        it,
                        stream
                    )
                }

                TransactionType.ReleaseItem -> (value.transactionData as ReleaseItemTransactionData).let {
                    ReleaseItemTransactionData.serialize(
                        it,
                        stream
                    )
                }

                else -> Unit
            }
        }

        override fun deserialize(stream: Source): InventoryTransactionPacket {
            val legacyRequestID: Int
            val transactionType: TransactionType
            return InventoryTransactionPacket(
                legacyRequestID = ProtoVAR.Int.deserialize(stream).also { legacyRequestID = it },
                legacySetItemSlots = when (legacyRequestID != 0) {
                    true -> ProtoHelper.deserializeList(stream, LegacySetItemSlotData)
                    false -> null
                },
                transactionType = TransactionType.deserialize(stream).also { transactionType = it },
                actions = ProtoHelper.deserializeList(stream, InventoryAction),
                transactionData = when (transactionType) {
                    TransactionType.UseItem -> UseItemTransactionData.deserialize(stream)
                    TransactionType.UseItemOnEntity -> UseItemOnEntityTransactionData.deserialize(stream)
                    TransactionType.ReleaseItem -> ReleaseItemTransactionData.deserialize(stream)
                    else -> null
                }
            )
        }
    }
}
