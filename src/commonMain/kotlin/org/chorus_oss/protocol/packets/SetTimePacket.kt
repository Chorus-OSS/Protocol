package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int


data class SetTimePacket(
    val time: Int
) : Packet(id) {
    companion object : PacketCodec<SetTimePacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.SET_TIME_PACKET

        override fun serialize(value: SetTimePacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.time, stream)
        }

        override fun deserialize(stream: Source): SetTimePacket {
            return SetTimePacket(
                time = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
