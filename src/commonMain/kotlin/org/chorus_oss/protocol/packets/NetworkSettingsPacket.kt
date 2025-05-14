package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.PacketCompressionAlgorithm


class NetworkSettingsPacket : Packet(id) {
    var compressionThreshold: Int = 0
    var compressionAlgorithm: PacketCompressionAlgorithm? = null
    var clientThrottleEnabled: Boolean = false
    var clientThrottleThreshold: Byte = 0
    var clientThrottleScalar: Float = 0f

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeShortLE(this.compressionThreshold)
        byteBuf.writeShortLE(compressionAlgorithm!!.ordinal)
        byteBuf.writeBoolean(this.clientThrottleEnabled)
        byteBuf.writeByte(clientThrottleThreshold.toInt())
        byteBuf.writeFloatLE(this.clientThrottleScalar)
    }

    override fun pid(): Int {
        return ProtocolInfo.NETWORK_SETTINGS_PACKET
    }



    companion object : PacketCodec<NetworkSettingsPacket> {
        override fun deserialize(stream: Source): NetworkSettingsPacket {
            val packet = NetworkSettingsPacket()

            packet.compressionThreshold = byteBuf.readShortLE().toInt()
            packet.compressionAlgorithm = PacketCompressionAlgorithm.entries[byteBuf.readShortLE().toInt()]
            packet.clientThrottleEnabled = Proto.Boolean.deserialize(stream)
            packet.clientThrottleThreshold = Proto.Byte.deserialize(stream)
            packet.clientThrottleScalar = byteBuf.readFloatLE()

            return packet
        }
    }
}
