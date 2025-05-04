package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

class ServerSettingsRequestPacket : DataPacket() {
    override fun pid(): Int {
        return ProtocolInfo.SERVER_SETTINGS_REQUEST_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<ServerSettingsRequestPacket> {
        override fun decode(byteBuf: ByteBuf): ServerSettingsRequestPacket {
            return ServerSettingsRequestPacket()
        }
    }
}
