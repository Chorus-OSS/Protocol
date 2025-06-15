package org.chorus_oss.protocol.types.inventory.transaction

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.item.ItemStack


data class InventoryAction(
    val sourceType: Type,
    val windowID: Int?,
    val sourceFlags: Flag?,
    val inventorySlot: UInt,
    val oldItem: ItemStack,
    val newItem: ItemStack,
) {
    companion object : ProtoCodec<InventoryAction> {
        enum class Type(private val id: UInt) {
            Container(0u),
            WorldInteraction(2u),
            Creative(3u),
            NonImplementedTODO(99999u);

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    ProtoVAR.UInt.serialize(value.id.toUInt(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return ProtoVAR.UInt.deserialize(stream).let {
                        entries.find { e -> e.id == it }!!
                    }
                }
            }
        }

        enum class Flag {
            DropItem,
            PickupItem,
            None;

            companion object : ProtoCodec<Flag> {
                override fun serialize(
                    value: Flag,
                    stream: Sink
                ) {
                    ProtoVAR.UInt.serialize(value.ordinal.toUInt(), stream)
                }

                override fun deserialize(stream: Source): Flag {
                    return entries[ProtoVAR.UInt.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(
            value: InventoryAction,
            stream: Sink
        ) {
            Type.serialize(value.sourceType, stream)
            when (value.sourceType) {
                Type.Container,
                Type.NonImplementedTODO -> (value.windowID as Int).let { ProtoVAR.Int.serialize(it, stream) }

                Type.WorldInteraction -> (value.sourceFlags as Flag).let { Flag.serialize(it, stream) }

                else -> Unit
            }
            ProtoVAR.UInt.serialize(value.inventorySlot, stream)
            ItemStack.serialize(value.oldItem, stream)
            ItemStack.serialize(value.newItem, stream)
        }

        override fun deserialize(stream: Source): InventoryAction {
            val type: Type
            return InventoryAction(
                sourceType = Type.deserialize(stream).also { type = it },
                windowID = when (type) {
                    Type.Container, Type.NonImplementedTODO -> ProtoVAR.Int.deserialize(stream)

                    else -> null
                },
                sourceFlags = when (type) {
                    Type.WorldInteraction -> Flag.deserialize(stream)

                    else -> null
                },
                inventorySlot = ProtoVAR.UInt.deserialize(stream),
                oldItem = ItemStack.deserialize(stream),
                newItem = ItemStack.deserialize(stream)
            )
        }
    }
}
