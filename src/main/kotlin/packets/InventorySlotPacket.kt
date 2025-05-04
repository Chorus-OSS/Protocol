package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.item.Item

import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.inventory.FullContainerName
import org.chorus_oss.protocol.types.itemstack.ContainerSlotType


class InventorySlotPacket : DataPacket(), PacketEncoder {
    var inventoryId: Int = 0
    var slot: Int = 0
    var fullContainerName: FullContainerName = FullContainerName(ContainerSlotType.ANVIL_INPUT, null)
    var storageItem: Item = Item.AIR // is air if the item is not a bundle
    var item: Item? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.inventoryId)
        byteBuf.writeUnsignedVarInt(this.slot)
        byteBuf.writeFullContainerName(this.fullContainerName)
        byteBuf.writeSlot(this.storageItem)
        byteBuf.writeSlot(this.item)
    }

    override fun pid(): Int {
        return ProtocolInfo.INVENTORY_SLOT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
