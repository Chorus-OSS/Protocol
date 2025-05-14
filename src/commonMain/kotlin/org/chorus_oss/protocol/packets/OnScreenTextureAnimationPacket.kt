package org.chorus_oss.protocol.packets


class OnScreenTextureAnimationPacket : Packet(id) {
    var effectId: Int = 0

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeIntLE(this.effectId)
    }

    override fun pid(): Int {
        return ProtocolInfo.ON_SCREEN_TEXTURE_ANIMATION_PACKET
    }



    companion object : PacketCodec<OnScreenTextureAnimationPacket> {
        override fun deserialize(stream: Source): OnScreenTextureAnimationPacket {
            val packet = OnScreenTextureAnimationPacket()

            packet.effectId = byteBuf.readIntLE()

            return packet
        }
    }
}
