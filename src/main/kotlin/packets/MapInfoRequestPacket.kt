package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

class MapInfoRequestPacket : DataPacket() {
    var mapId: Long = 0

    override fun pid(): Int {
        return ProtocolInfo.MAP_INFO_REQUEST_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }

    companion object : PacketDecoder<MapInfoRequestPacket> {
        override fun decode(byteBuf: ByteBuf): MapInfoRequestPacket {
            val packet = MapInfoRequestPacket()
            packet.mapId = byteBuf.readActorUniqueID()
            return packet
        }
    }
}
