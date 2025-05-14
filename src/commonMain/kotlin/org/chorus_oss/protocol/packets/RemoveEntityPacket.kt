package org.chorus_oss.protocol.packets


class RemoveEntityPacket : Packet(id) {
    @JvmField
    var eid: Long = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.eid)
    }

    override fun pid(): Int {
        return ProtocolInfo.REMOVE_ENTITY_PACKET
    }


}
