package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.ActorLink

class SetEntityLinkPacket : Packet(id) {
    var vehicleUniqueId: Long = 0 //from
    var riderUniqueId: Long = 0 //to
    var type: ActorLink.Type? = null
    var immediate: Byte = 0
    var riderInitiated: Boolean = false
    var vehicleAngularVelocity: Float = 0f

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.vehicleUniqueId)
        byteBuf.writeActorUniqueID(this.riderUniqueId)
        byteBuf.writeByte(type!!.ordinal.toByte().toInt())
        byteBuf.writeByte(immediate.toInt())
        byteBuf.writeBoolean(this.riderInitiated)
        byteBuf.writeFloatLE(this.vehicleAngularVelocity)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_ENTITY_LINK_PACKET
    }


}
