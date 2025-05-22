package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte


data class SimulationTypePacket(
    val simulationType: SimulationType,
) : Packet(id) {
    companion object : PacketCodec<SimulationTypePacket> {
        init { PacketRegistry.register(this) }

        enum class SimulationType {
            Game,
            Editor,
            Test,
            Invalid;

            companion object : ProtoCodec<SimulationType> {
                override fun serialize(
                    value: SimulationType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): SimulationType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.SIMULATION_TYPE_PACKET

        override fun serialize(value: SimulationTypePacket, stream: Sink) {
            SimulationType.serialize(value.simulationType, stream)
        }

        override fun deserialize(stream: Source): SimulationTypePacket {
            return SimulationTypePacket(
                simulationType = SimulationType.deserialize(stream),
            )
        }
    }
}
