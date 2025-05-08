package org.chorus_oss.protocol.packets


class StopSoundPacket : DataPacket() {
    var name: String? = null
    var stopAll: Boolean = false
    var stopMusicLegacy: Boolean = false

    override fun decode(byteBuf: ByteBuf) {
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(name!!)
        byteBuf.writeBoolean(this.stopAll)
        byteBuf.writeBoolean(this.stopMusicLegacy)
    }

    override fun pid(): Int {
        return ProtocolInfo.STOP_SOUND_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
