package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.world.DimensionDefinition


class DimensionDataPacket(
    val definitions: List<DimensionDefinition>
) : Packet(id) {
    companion object : PacketCodec<DimensionDataPacket> {
        override val id: Int
            get() = ProtocolInfo.DIMENSION_DATA_PACKET

        override fun serialize(value: DimensionDataPacket, stream: Sink) {
            ProtoHelper.serializeList(value.definitions, stream, DimensionDefinition::serialize)
        }

        override fun deserialize(stream: Source): DimensionDataPacket {
            return DimensionDataPacket(
                definitions = ProtoHelper.deserializeList(stream, DimensionDefinition::deserialize)
            )
        }
    }
}
