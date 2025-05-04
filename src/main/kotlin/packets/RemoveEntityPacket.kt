package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class RemoveEntityPacket : DataPacket() {
    @JvmField
    var eid: Long = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.eid)
    }

    override fun pid(): Int {
        return ProtocolInfo.REMOVE_ENTITY_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
