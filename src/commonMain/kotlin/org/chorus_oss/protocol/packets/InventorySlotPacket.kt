package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.inventory.FullContainerName
import org.chorus_oss.protocol.types.item.ItemStack


data class InventorySlotPacket(
    val windowID: UInt,
    val slot: UInt,
    val container: FullContainerName,
    val storageItem: ItemStack,
    val newItem: ItemStack,
) : Packet(id) {
    companion object : PacketCodec<InventorySlotPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.INVENTORY_SLOT_PACKET

        override fun serialize(value: InventorySlotPacket, stream: Sink) {
            ProtoVAR.UInt.serialize(value.windowID, stream)
            ProtoVAR.UInt.serialize(value.slot, stream)
            FullContainerName.serialize(value.container, stream)
            ItemStack.serialize(value.storageItem, stream)
            ItemStack.serialize(value.newItem, stream)
        }

        override fun deserialize(stream: Source): InventorySlotPacket {
            return InventorySlotPacket(
                windowID = ProtoVAR.UInt.deserialize(stream),
                slot = ProtoVAR.UInt.deserialize(stream),
                container = FullContainerName.deserialize(stream),
                storageItem = ItemStack.deserialize(stream),
                newItem = ItemStack.deserialize(stream),
            )
        }
    }
}
