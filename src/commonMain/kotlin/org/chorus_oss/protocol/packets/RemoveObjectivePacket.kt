package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String


data class RemoveObjectivePacket(
    val objectiveName: String,
) : Packet(id) {
    companion object : PacketCodec<RemoveObjectivePacket> {
        override val id: Int = 106

        override fun serialize(value: RemoveObjectivePacket, stream: Sink) {
            Proto.String.serialize(value.objectiveName, stream)
        }

        override fun deserialize(stream: Source): RemoveObjectivePacket {
            return RemoveObjectivePacket(
                objectiveName = Proto.String.deserialize(stream),
            )
        }
    }
}
