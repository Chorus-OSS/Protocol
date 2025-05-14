package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.inventory.SpecialWindowId

class PlayerHotbarPacket : Packet(id) {
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



    companion object : PacketCodec<PlayerHotbarPacket> {
        override fun deserialize(stream: Source): PlayerHotbarPacket {
            val packet = PlayerHotbarPacket()

            packet.selectedHotbarSlot = byteBuf.readUnsignedVarInt()
            packet.windowId = Proto.Byte.deserialize(stream).toInt()
            packet.selectHotbarSlot = Proto.Boolean.deserialize(stream)

            return packet
        }
    }
}
