package org.chorus_oss.protocol.packets


class SimulationTypePacket : Packet(id) {
    var type: SimulationType = SimulationType.GAME

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(type.ordinal.toByte().toInt())
    }

    enum class SimulationType {
        GAME,
        EDITOR,
        TEST
    }

    override fun pid(): Int {
        return ProtocolInfo.SIMULATION_TYPE_PACKET
    }



    companion object : PacketCodec<SimulationTypePacket> {
        override fun deserialize(stream: Source): SimulationTypePacket {
            val packet = SimulationTypePacket()

            packet.type = TYPES[Proto.Byte.deserialize(stream).toInt()]

            return packet
        }

        private val TYPES = SimulationType.entries.toTypedArray()
    }
}
