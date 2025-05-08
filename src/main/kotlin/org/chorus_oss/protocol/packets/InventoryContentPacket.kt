package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.item.Item

import org.chorus_oss.protocol.types.inventory.FullContainerName
import org.chorus_oss.protocol.types.itemstack.ContainerSlotType

class InventoryContentPacket : DataPacket() {
    var inventoryId: Int = 0
    var slots: Array<Item> = Item.EMPTY_ARRAY
    var fullContainerName: FullContainerName = FullContainerName(ContainerSlotType.ANVIL_INPUT, null)
    var storageItem: Item = Item.AIR // is air if the item is not a bundle

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.inventoryId)
        byteBuf.writeUnsignedVarInt(slots.size)
        for (slot in this.slots) {
            byteBuf.writeSlot(slot)
        }
        byteBuf.writeFullContainerName(this.fullContainerName)
        byteBuf.writeSlot(this.storageItem)
    }

    override fun pid(): Int {
        return ProtocolInfo.INVENTORY_CONTENT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
