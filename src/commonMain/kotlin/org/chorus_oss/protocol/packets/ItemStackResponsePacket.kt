package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.itemstack.response.ItemStackResponse
import org.chorus_oss.protocol.types.itemstack.response.ItemStackResponseContainer
import org.chorus_oss.protocol.types.itemstack.response.ItemStackResponseSlot
import org.chorus_oss.protocol.types.itemstack.response.ItemStackResponseStatus


class ItemStackResponsePacket : Packet(id) {
    val entries: MutableList<ItemStackResponse> = ArrayList()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeArray(entries) { r: ItemStackResponse ->
            byteBuf.writeByte(r.result.ordinal)
            byteBuf.writeVarInt(r.requestId)
            if (r.result != ItemStackResponseStatus.OK) return@writeArray
            byteBuf.writeArray(
                r.containers
            ) { container: ItemStackResponseContainer ->
                byteBuf.writeFullContainerName(container.containerName)
                byteBuf.writeArray(
                    container.items
                ) { item: ItemStackResponseSlot ->
                    byteBuf.writeByte(item.slot)
                    byteBuf.writeByte(item.hotbarSlot)
                    byteBuf.writeByte(item.count)
                    byteBuf.writeVarInt(item.stackNetworkId)
                    byteBuf.writeString(item.customName)
                    byteBuf.writeString(item.filteredCustomName)
                    byteBuf.writeVarInt(item.durabilityCorrection)
                }
            }
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.ITEM_STACK_RESPONSE_PACKET
    }


}
