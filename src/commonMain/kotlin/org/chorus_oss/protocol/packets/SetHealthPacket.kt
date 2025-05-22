package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int


data class SetHealthPacket(
    val health: Int
) : Packet(id) {
    companion object : PacketCodec<SetHealthPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.SET_HEALTH_PACKET

        override fun serialize(value: SetHealthPacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.health, stream)
        }

        override fun deserialize(stream: Source): SetHealthPacket {
            return SetHealthPacket(
                health = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
