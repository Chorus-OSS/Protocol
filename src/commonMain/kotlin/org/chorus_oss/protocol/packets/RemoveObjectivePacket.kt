package org.chorus_oss.protocol.packets


class RemoveObjectivePacket : Packet(id) {
    @JvmField
    var objectiveName: String? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(objectiveName!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.REMOVE_OBJECTIVE_PACKET
    }


}
