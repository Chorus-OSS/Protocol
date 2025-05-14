package org.chorus_oss.protocol.packets

import org.chorus_oss.protocol.shared.types.Vector3f


class ToggleCrafterSlotRequestPacket : Packet(id) {
    var blockPosition: Vector3f? = null
    var slot: Byte = 0
    var disabled: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVector3f(blockPosition!!)
        byteBuf.writeByte(slot.toInt())
        byteBuf.writeBoolean(this.disabled)
    }

    override fun pid(): Int {
        return ProtocolInfo.TOGGLE_CRAFTER_SLOT_REQUEST
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ToggleCrafterSlotRequestPacket> {
        override fun decode(byteBuf: ByteBuf): ToggleCrafterSlotRequestPacket {
            val packet = ToggleCrafterSlotRequestPacket()

            packet.blockPosition = byteBuf.readVector3f()
            packet.slot = byteBuf.readByte()
            packet.disabled = byteBuf.readBoolean()

            return packet
        }
    }
}
