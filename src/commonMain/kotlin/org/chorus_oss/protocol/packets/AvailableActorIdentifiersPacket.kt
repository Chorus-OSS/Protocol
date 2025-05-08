package org.chorus_oss.protocol.packets


data class AvailableActorIdentifiersPacket(
    val tag: ByteArray,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBytes(tag)
    }

    override fun pid(): Int {
        return ProtocolInfo.AVAILABLE_ACTOR_IDENTIFIERS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AvailableActorIdentifiersPacket

        return tag.contentEquals(other.tag)
    }

    override fun hashCode(): Int {
        return tag.contentHashCode()
    }
}


