package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int


data class SetTimePacket(
    val time: Int
) : Packet(id) {
    companion object : PacketCodec<SetTimePacket> {
        override val id: Int = 10

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
