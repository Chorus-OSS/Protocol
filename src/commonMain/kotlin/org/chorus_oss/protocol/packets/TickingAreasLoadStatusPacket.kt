package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean


data class TickingAreasLoadStatusPacket(
    val preload: Boolean
) : Packet(id) {
    companion object : PacketCodec<TickingAreasLoadStatusPacket> {
        override val id: Int = 179

        override fun serialize(
            value: TickingAreasLoadStatusPacket,
            stream: Sink
        ) {
            Proto.Boolean.serialize(value.preload, stream)
        }

        override fun deserialize(stream: Source): TickingAreasLoadStatusPacket {
            return TickingAreasLoadStatusPacket(
                preload = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
