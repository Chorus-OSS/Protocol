package org.chorus_oss.protocol.packets

import org.chorus_oss.protocol.types.Vector3f


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



    companion object : PacketCodec<ToggleCrafterSlotRequestPacket> {
        override fun deserialize(stream: Source): ToggleCrafterSlotRequestPacket {
            val packet = ToggleCrafterSlotRequestPacket()

            packet.blockPosition = Vector3f.deserialize(stream)
            packet.slot = Proto.Byte.deserialize(stream)
            packet.disabled = Proto.Boolean.deserialize(stream)

            return packet
        }
    }
}
