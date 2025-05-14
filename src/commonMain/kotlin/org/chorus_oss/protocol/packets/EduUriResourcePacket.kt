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



    companion object : PacketCodec<EduUriResourcePacket> {
        override fun deserialize(stream: Source): EduUriResourcePacket {
            val packet = EduUriResourcePacket()

            val buttonName = Proto.String.deserialize(stream)
            val linkUri = Proto.String.deserialize(stream)
            packet.eduSharedUriResource = EduSharedUriResource(buttonName, linkUri)

            return packet
        }
    }
}
