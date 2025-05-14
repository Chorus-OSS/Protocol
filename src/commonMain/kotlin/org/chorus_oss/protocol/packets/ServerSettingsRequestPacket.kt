package org.chorus_oss.protocol.packets


class ServerSettingsRequestPacket : Packet(id) {
    override fun pid(): Int {
        return ProtocolInfo.SERVER_SETTINGS_REQUEST_PACKET
    }



    companion object : PacketCodec<ServerSettingsRequestPacket> {
        override fun deserialize(stream: Source): ServerSettingsRequestPacket {
            return ServerSettingsRequestPacket()
        }
    }
}
