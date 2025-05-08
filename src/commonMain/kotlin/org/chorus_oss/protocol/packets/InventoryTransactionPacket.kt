package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.LegacySetItemSlotData
import org.chorus_oss.protocol.types.inventory.transaction.*
import org.chorus_oss.protocol.types.inventory.transaction.UseItemData.PredictedResult
import org.chorus_oss.protocol.types.inventory.transaction.UseItemData.TriggerType

class InventoryTransactionPacket(
    val legacyRequestID: Int,
    val legacySetItemSlots: List<LegacySetItemSlotData>,
    val transactionType: TransactionType,
    val actions: Array<NetworkInventoryAction>,
    val transactionData: TransactionData?,
) : DataPacket(), PacketEncoder {

    enum class TransactionType {
        NORMAL,
        MISMATCH,
        USE_ITEM,
        USE_ITEM_ON_ENTITY,
        RELEASE_ITEM;

        companion object {
            fun fromOrdinal(ordinal: Int): TransactionType {
                return entries.find { it.ordinal == ordinal }
                    ?: throw RuntimeException("Unknown TransactionType Ordinal: $ordinal")
            }
        }
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.legacyRequestID)
        byteBuf.writeUnsignedVarInt(this.transactionType.ordinal)

        if (legacyRequestID != 0) {
            byteBuf.writeUnsignedVarInt(legacySetItemSlots.size)
            for (slot in legacySetItemSlots) {
                byteBuf.writeByte(slot.containerId)
                byteBuf.writeByteArray(slot.slots)
            }
        }

        byteBuf.writeUnsignedVarInt(actions.size)
        for (action in this.actions) {
            action.write(byteBuf)
        }

        when (this.transactionType) {
            TransactionType.USE_ITEM -> {
                val useItemData = transactionData as UseItemData

                byteBuf.writeUnsignedVarInt(useItemData.actionType)
                byteBuf.writeUnsignedVarInt(useItemData.triggerType.ordinal)
                byteBuf.writeBlockVector3(useItemData.blockPos)
                byteBuf.writeBlockFace(useItemData.face)
                byteBuf.writeVarInt(useItemData.hotbarSlot)
                byteBuf.writeSlot(useItemData.itemInHand)
                byteBuf.writeVector3f(useItemData.playerPos.asVector3f())
                byteBuf.writeVector3f(useItemData.clickPos)
                byteBuf.writeUnsignedVarInt(useItemData.blockRuntimeId)
                byteBuf.writeUnsignedVarInt(useItemData.clientInteractPrediction.ordinal)
            }

            TransactionType.USE_ITEM_ON_ENTITY -> {
                val useItemOnEntityData = transactionData as UseItemOnEntityData

                byteBuf.writeActorRuntimeID(useItemOnEntityData.entityRuntimeId)
                byteBuf.writeUnsignedVarInt(useItemOnEntityData.actionType)
                byteBuf.writeVarInt(useItemOnEntityData.hotbarSlot)
                byteBuf.writeSlot(useItemOnEntityData.itemInHand)
                byteBuf.writeVector3f(useItemOnEntityData.playerPos.asVector3f())
                byteBuf.writeVector3f(useItemOnEntityData.clickPos.asVector3f())
            }

            TransactionType.RELEASE_ITEM -> {
                val releaseItemData = transactionData as ReleaseItemData

                byteBuf.writeUnsignedVarInt(releaseItemData.actionType)
                byteBuf.writeVarInt(releaseItemData.hotbarSlot)
                byteBuf.writeSlot(releaseItemData.itemInHand)
                byteBuf.writeVector3f(releaseItemData.headRot.asVector3f())
            }

            else -> Unit
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.INVENTORY_TRANSACTION_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<InventoryTransactionPacket> {
        override fun decode(byteBuf: ByteBuf): InventoryTransactionPacket {
            val legacyRequestID: Int
            val transactionType: TransactionType
            return InventoryTransactionPacket(
                legacyRequestID = byteBuf.readUnsignedVarInt()
                    .also { legacyRequestID = it },
                legacySetItemSlots = when (legacyRequestID != 0) {
                    true -> List(byteBuf.readUnsignedVarInt()) {
                        LegacySetItemSlotData(
                            byteBuf.readByte().toInt(),
                            byteBuf.readByteArray()
                        )
                    }

                    false -> emptyList()
                },
                transactionType = TransactionType.fromOrdinal(byteBuf.readUnsignedVarInt())
                    .also { transactionType = it },
                actions = Array(byteBuf.readUnsignedVarInt()) {
                    NetworkInventoryAction.read(byteBuf)
                },
                transactionData = when (transactionType) {
                    TransactionType.USE_ITEM -> UseItemData(
                        actionType = byteBuf.readUnsignedVarInt(),
                        triggerType = TriggerType.entries[byteBuf.readUnsignedVarInt()],
                        blockPos = byteBuf.readBlockVector3(),
                        face = byteBuf.readBlockFace(),
                        hotbarSlot = byteBuf.readVarInt(),
                        itemInHand = byteBuf.readSlot(),
                        playerPos = byteBuf.readVector3f().asVector3(),
                        clickPos = byteBuf.readVector3f(),
                        blockRuntimeId = byteBuf.readUnsignedVarInt(),
                        clientInteractPrediction = PredictedResult.entries[byteBuf.readUnsignedVarInt()],
                    )

                    TransactionType.USE_ITEM_ON_ENTITY -> UseItemOnEntityData(
                        entityRuntimeId = byteBuf.readActorRuntimeID(),
                        actionType = byteBuf.readUnsignedVarInt(),
                        hotbarSlot = byteBuf.readVarInt(),
                        itemInHand = byteBuf.readSlot(),
                        playerPos = byteBuf.readVector3f().asVector3(),
                        clickPos = byteBuf.readVector3f().asVector3(),
                    )

                    TransactionType.RELEASE_ITEM -> ReleaseItemData(
                        actionType = byteBuf.readUnsignedVarInt(),
                        hotbarSlot = byteBuf.readVarInt(),
                        itemInHand = byteBuf.readSlot(),
                        headRot = byteBuf.readVector3f().asVector3()
                    )

                    else -> null
                }
            )
        }

        const val USE_ITEM_ACTION_CLICK_BLOCK: Int = 0
        const val USE_ITEM_ACTION_CLICK_AIR: Int = 1
        const val USE_ITEM_ACTION_BREAK_BLOCK: Int = 2

        const val RELEASE_ITEM_ACTION_RELEASE: Int = 0 // bow shoot
        const val RELEASE_ITEM_ACTION_CONSUME: Int = 1 // eat food, drink potion

        const val USE_ITEM_ON_ENTITY_ACTION_INTERACT: Int = 0
        const val USE_ITEM_ON_ENTITY_ACTION_ATTACK: Int = 1

        const val ACTION_MAGIC_SLOT_DROP_ITEM: Int = 0
        const val ACTION_MAGIC_SLOT_PICKUP_ITEM: Int = 1

        const val ACTION_MAGIC_SLOT_CREATIVE_DELETE_ITEM: Int = 0
        const val ACTION_MAGIC_SLOT_CREATIVE_CREATE_ITEM: Int = 1
    }
}
