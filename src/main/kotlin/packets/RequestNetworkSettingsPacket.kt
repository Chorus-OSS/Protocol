package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo


class RequestNetworkSettingsPacket : DataPacket() {
    var protocolVersion: Int = 0

    override fun pid(): Int {
        return ProtocolInfo.REQUEST_NETWORK_SETTINGS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<RequestNetworkSettingsPacket> {
        override fun decode(byteBuf: ByteBuf): RequestNetworkSettingsPacket {
            val packet = RequestNetworkSettingsPacket()

            packet.protocolVersion = byteBuf.readInt()

            return packet
        }
    }
}
