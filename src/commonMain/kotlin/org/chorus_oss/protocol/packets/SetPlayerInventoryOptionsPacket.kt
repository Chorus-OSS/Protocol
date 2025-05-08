package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.inventory.InventoryLayout
import org.chorus_oss.protocol.types.inventory.InventoryTabLeft
import org.chorus_oss.protocol.types.inventory.InventoryTabRight

class SetPlayerInventoryOptionsPacket : DataPacket() {
    var leftTab: InventoryTabLeft? = null
    var rightTab: InventoryTabRight? = null
    var filtering: Boolean = false
    var layout: InventoryLayout? = null
    var craftingLayout: InventoryLayout? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(leftTab!!.ordinal)
        byteBuf.writeVarInt(rightTab!!.ordinal)
        byteBuf.writeBoolean(this.filtering)
        byteBuf.writeVarInt(layout!!.ordinal)
        byteBuf.writeVarInt(craftingLayout!!.ordinal)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_PLAYER_INVENTORY_OPTIONS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<SetPlayerInventoryOptionsPacket> {
        override fun decode(byteBuf: ByteBuf): SetPlayerInventoryOptionsPacket {
            val packet = SetPlayerInventoryOptionsPacket()

            packet.leftTab = InventoryTabLeft.VALUES[byteBuf.readVarInt()]
            packet.rightTab = InventoryTabRight.VALUES[byteBuf.readVarInt()]
            packet.filtering = byteBuf.readBoolean()
            packet.layout = InventoryLayout.VALUES[byteBuf.readVarInt()]
            packet.craftingLayout = InventoryLayout.VALUES[byteBuf.readVarInt()]

            return packet
        }
    }
}
