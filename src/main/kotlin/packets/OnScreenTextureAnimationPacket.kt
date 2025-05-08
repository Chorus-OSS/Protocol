package org.chorus_oss.protocol.packets


class OnScreenTextureAnimationPacket : DataPacket() {
    var effectId: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeIntLE(this.effectId)
    }

    override fun pid(): Int {
        return ProtocolInfo.ON_SCREEN_TEXTURE_ANIMATION_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<OnScreenTextureAnimationPacket> {
        override fun decode(byteBuf: ByteBuf): OnScreenTextureAnimationPacket {
            val packet = OnScreenTextureAnimationPacket()

            packet.effectId = byteBuf.readIntLE()

            return packet
        }
    }
}
