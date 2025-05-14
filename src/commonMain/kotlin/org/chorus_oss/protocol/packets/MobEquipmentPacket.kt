package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.item.Item


class MobEquipmentPacket : Packet(id) {
    var eid: Long = 0
    lateinit var item: Item
    var slot: Int = 0
    var selectedSlot: Int = 0
    var containerId: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(this.eid)
        byteBuf.writeSlot(this.item)
        byteBuf.writeByte(slot)
        byteBuf.writeByte(selectedSlot)
        byteBuf.writeByte(containerId)
    }

    override fun pid(): Int {
        return ProtocolInfo.MOB_EQUIPMENT_PACKET
    }



    companion object : PacketCodec<MobEquipmentPacket> {
        override fun deserialize(stream: Source): MobEquipmentPacket {
            val packet = MobEquipmentPacket()
            packet.eid = byteBuf.readActorRuntimeID()
            packet.item = byteBuf.readSlot()
            packet.slot = Proto.Byte.deserialize(stream).toInt()
            packet.selectedSlot = Proto.Byte.deserialize(stream).toInt()
            packet.containerId = Proto.Byte.deserialize(stream).toInt()
            return packet
        }
    }
}
