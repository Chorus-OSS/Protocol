package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.item.ItemStack

data class MobArmorEquipmentPacket(
    val entityRuntimeID: ActorRuntimeID,
    val head: ItemStack,
    val torso: ItemStack,
    val legs: ItemStack,
    val feet: ItemStack,
    val body: ItemStack,
) : Packet(id) {
    companion object : PacketCodec<MobArmorEquipmentPacket> {
        override val id: Int
            get() = ProtocolInfo.MOB_ARMOR_EQUIPMENT_PACKET

        override fun serialize(
            value: MobArmorEquipmentPacket,
            stream: Sink
        ) {
            ActorRuntimeID.serialize(value.entityRuntimeID, stream)
            ItemStack.serialize(value.head, stream)
            ItemStack.serialize(value.torso, stream)
            ItemStack.serialize(value.legs, stream)
            ItemStack.serialize(value.feet, stream)
            ItemStack.serialize(value.body, stream)
        }

        override fun deserialize(stream: Source): MobArmorEquipmentPacket {
            return MobArmorEquipmentPacket(
                entityRuntimeID = ActorRuntimeID.deserialize(stream),
                head = ItemStack.deserialize(stream),
                torso = ItemStack.deserialize(stream),
                legs = ItemStack.deserialize(stream),
                feet = ItemStack.deserialize(stream),
                body = ItemStack.deserialize(stream)
            )
        }
    }
}
