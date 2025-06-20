package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String

data class CurrentStructureFeaturePacket(
    val currentFeature: String,
) : Packet(id) {
    companion object : PacketCodec<CurrentStructureFeaturePacket> {
        override val id: Int = 314

        override fun serialize(
            value: CurrentStructureFeaturePacket,
            stream: Sink
        ) {
            Proto.String.serialize(value.currentFeature, stream)
        }

        override fun deserialize(stream: Source): CurrentStructureFeaturePacket {
            return CurrentStructureFeaturePacket(
                currentFeature = Proto.String.deserialize(stream),
            )
        }
    }
}
