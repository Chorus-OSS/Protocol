package org.chorus_oss.protocol.packets


class MapInfoRequestPacket : Packet(id) {
    var mapId: Long = 0

    override fun pid(): Int {
        return ProtocolInfo.MAP_INFO_REQUEST_PACKET
    }



    companion object : PacketCodec<MapInfoRequestPacket> {
        override fun deserialize(stream: Source): MapInfoRequestPacket {
            val packet = MapInfoRequestPacket()
            packet.mapId = ActorUniqueID.deserialize(stream)
            return packet
        }
    }
}
