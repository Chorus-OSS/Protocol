package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.types.inventory.InventoryLayout
import org.chorus_oss.protocol.types.inventory.InventoryTabLeft
import org.chorus_oss.protocol.types.inventory.InventoryTabRight

data class SetPlayerInventoryOptionsPacket(
    val leftInventoryTab: InventoryTabLeft,
    val rightInventoryTab: InventoryTabRight,
    val filtering: Boolean,
    val inventoryLayout: InventoryLayout,
    val craftingLayout: InventoryLayout,
) : Packet(id) {
    companion object : PacketCodec<SetPlayerInventoryOptionsPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.SET_PLAYER_INVENTORY_OPTIONS_PACKET

        override fun serialize(
            value: SetPlayerInventoryOptionsPacket,
            stream: Sink
        ) {
            InventoryTabLeft.serialize(value.leftInventoryTab, stream)
            InventoryTabRight.serialize(value.rightInventoryTab, stream)
            Proto.Boolean.serialize(value.filtering, stream)
            InventoryLayout.serialize(value.inventoryLayout, stream)
            InventoryLayout.serialize(value.craftingLayout, stream)
        }

        override fun deserialize(stream: Source): SetPlayerInventoryOptionsPacket {
            return SetPlayerInventoryOptionsPacket(
                leftInventoryTab = InventoryTabLeft.deserialize(stream),
                rightInventoryTab = InventoryTabRight.deserialize(stream),
                filtering = Proto.Boolean.deserialize(stream),
                inventoryLayout = InventoryLayout.deserialize(stream),
                craftingLayout = InventoryLayout.deserialize(stream),
            )
        }
    }
}
