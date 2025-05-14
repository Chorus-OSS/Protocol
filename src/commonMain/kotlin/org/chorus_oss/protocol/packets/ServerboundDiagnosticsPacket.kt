package org.chorus_oss.protocol.packets


class ServerboundDiagnosticsPacket : Packet(id) {
    var avgFps: Float = 0f
    var avgServerSimTickTimeMS: Float = 0f
    var avgClientSimTickTimeMS: Float = 0f
    var avgBeginFrameTimeMS: Float = 0f
    var avgInputTimeMS: Float = 0f
    var avgRenderTimeMS: Float = 0f
    var avgEndFrameTimeMS: Float = 0f
    var avgRemainderTimePercent: Float = 0f
    var avgUnaccountedTimePercent: Float = 0f

    override fun pid(): Int {
        return ProtocolInfo.SERVERBOUND_DIAGNOSTICS_PACKET
    }



    companion object : PacketCodec<ServerboundDiagnosticsPacket> {
        override fun deserialize(stream: Source): ServerboundDiagnosticsPacket {
            val packet = ServerboundDiagnosticsPacket()

            packet.avgFps = byteBuf.readFloatLE()
            packet.avgServerSimTickTimeMS = byteBuf.readFloatLE()
            packet.avgClientSimTickTimeMS = byteBuf.readFloatLE()
            packet.avgBeginFrameTimeMS = byteBuf.readFloatLE()
            packet.avgInputTimeMS = byteBuf.readFloatLE()
            packet.avgRenderTimeMS = byteBuf.readFloatLE()
            packet.avgEndFrameTimeMS = byteBuf.readFloatLE()
            packet.avgRemainderTimePercent = byteBuf.readFloatLE()
            packet.avgUnaccountedTimePercent = byteBuf.readFloatLE()

            return packet
        }
    }
}
