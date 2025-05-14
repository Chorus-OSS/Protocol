package org.chorus_oss.protocol.packets


class RequestNetworkSettingsPacket : Packet(id) {
    var protocolVersion: Int = 0

    override fun pid(): Int {
        return ProtocolInfo.REQUEST_NETWORK_SETTINGS_PACKET
    }



    companion object : PacketCodec<RequestNetworkSettingsPacket> {
        override fun deserialize(stream: Source): RequestNetworkSettingsPacket {
            val packet = RequestNetworkSettingsPacket()

            packet.protocolVersion = byteBuf.readInt()

            return packet
        }
    }
}
