package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.EduSharedUriResource


class EduUriResourcePacket : Packet(id) {
    var eduSharedUriResource: EduSharedUriResource? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(eduSharedUriResource!!.buttonName)
        byteBuf.writeString(eduSharedUriResource!!.linkUri)
    }

    override fun pid(): Int {
        return ProtocolInfo.EDU_URI_RESOURCE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<EduUriResourcePacket> {
        override fun decode(byteBuf: ByteBuf): EduUriResourcePacket {
            val packet = EduUriResourcePacket()

            val buttonName = byteBuf.readString()
            val linkUri = byteBuf.readString()
            packet.eduSharedUriResource = EduSharedUriResource(buttonName, linkUri)

            return packet
        }
    }
}
