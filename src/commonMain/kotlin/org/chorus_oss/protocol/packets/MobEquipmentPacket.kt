package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.item.ItemStack


data class MobEquipmentPacket(
    val entityRuntimeID: ActorRuntimeID,
    val newItem: ItemStack,
    val inventorySlot: Byte,
    val hotbarSlot: Byte,
    val windowID: Byte,
) : Packet(id) {
    companion object : PacketCodec<MobEquipmentPacket> {
        override val id: Int = 31

        override fun serialize(value: MobEquipmentPacket, stream: Sink) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            ItemStack.serialize(value.newItem, stream)
            Proto.Byte.serialize(value.inventorySlot, stream)
            Proto.Byte.serialize(value.hotbarSlot, stream)
            Proto.Byte.serialize(value.windowID, stream)
        }

        override fun deserialize(stream: Source): MobEquipmentPacket {
            return MobEquipmentPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                newItem = ItemStack.deserialize(stream),
                inventorySlot = Proto.Byte.deserialize(stream),
                hotbarSlot = Proto.Byte.deserialize(stream),
                windowID = Proto.Byte.deserialize(stream)
            )
        }
    }
}
