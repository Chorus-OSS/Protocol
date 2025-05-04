package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.inventory.SpecialWindowId

import org.chorus_oss.protocol.ProtocolInfo

class PlayerHotbarPacket : DataPacket() {
    var selectedHotbarSlot: Int = 0
    var windowId: Int = SpecialWindowId.PLAYER.id
    var selectHotbarSlot: Boolean = true

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeUnsignedVarInt(this.selectedHotbarSlot)
        byteBuf.writeByte(windowId.toByte().toInt())
        byteBuf.writeBoolean(this.selectHotbarSlot)
    }

    override fun pid(): Int {
        return ProtocolInfo.PLAYER_HOTBAR_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<PlayerHotbarPacket> {
        override fun decode(byteBuf: ByteBuf): PlayerHotbarPacket {
            val packet = PlayerHotbarPacket()

            packet.selectedHotbarSlot = byteBuf.readUnsignedVarInt()
            packet.windowId = byteBuf.readByte().toInt()
            packet.selectHotbarSlot = byteBuf.readBoolean()

            return packet
        }
    }
}
