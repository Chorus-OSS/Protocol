package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.inventory.FullContainerName
import org.chorus_oss.protocol.types.item.ItemStack

data class InventoryContentPacket(
    val windowID: UInt,
    val content: List<ItemStack>,
    val container: FullContainerName,
    val storageItem: ItemStack,
) : Packet(id) {
    companion object : PacketCodec<InventoryContentPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.INVENTORY_CONTENT_PACKET

        override fun serialize(value: InventoryContentPacket, stream: Sink) {
            ProtoVAR.UInt.serialize(value.windowID, stream)
            ProtoHelper.serializeList(value.content, stream, ItemStack)
            FullContainerName.serialize(value.container, stream)
            ItemStack.serialize(value.storageItem, stream)
        }

        override fun deserialize(stream: Source): InventoryContentPacket {
            return InventoryContentPacket(
                windowID = ProtoVAR.UInt.deserialize(stream),
                content = ProtoHelper.deserializeList(stream, ItemStack),
                container = FullContainerName.deserialize(stream),
                storageItem = ItemStack.deserialize(stream)
            )
        }
    }
}
