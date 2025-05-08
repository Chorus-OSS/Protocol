package org.chorus_oss.protocol.packets


class SetEntityMotionPacket : DataPacket() {
    @JvmField
    var eid: Long = 0

    @JvmField
    var motionX: Float = 0f

    @JvmField
    var motionY: Float = 0f

    @JvmField
    var motionZ: Float = 0f
    var tick: Long = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(this.eid)
        byteBuf.writeVector3f(this.motionX, this.motionY, this.motionZ)
        byteBuf.writeUnsignedVarLong(this.tick)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_ENTITY_MOTION_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
