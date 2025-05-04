package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.inventory.creative.CreativeItemData
import org.chorus_oss.protocol.types.inventory.creative.CreativeItemGroup
import org.chorus_oss.chorus.registry.CreativeItemRegistry
import org.chorus_oss.protocol.ProtocolInfo

data class CreativeContentPacket(
    val groups: List<CreativeItemGroup> = CreativeItemRegistry.creativeGroups.toList(),
    val writeEntries: List<CreativeItemData> = CreativeItemRegistry.creativeItemData.toList()
) : DataPacket() {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(0)
        byteBuf.writeVarInt(0)
        // TODO: FIX PACKET
//        byteBuf.writeArray(this.groups) { buf, group ->
//            this.writeGroup(buf, group)
//        }
//        byteBuf.writeArray(this.writeEntries) { buf, data ->
//            this.writeItem(buf, data)
//        }
    }

    private fun writeGroup(byteBuf: ByteBuf, group: CreativeItemGroup) {
        byteBuf.writeIntLE(group.category.ordinal)
        byteBuf.writeString(group.name)
        byteBuf.writeSlot(group.icon, true)
    }

    private fun writeItem(byteBuf: ByteBuf, data: CreativeItemData) {
        byteBuf.writeUnsignedVarInt(data.netID)
        byteBuf.writeSlot(data.item, true)
        byteBuf.writeUnsignedVarInt(data.groupId)
    }

    override fun pid(): Int {
        return ProtocolInfo.CREATIVE_CONTENT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
