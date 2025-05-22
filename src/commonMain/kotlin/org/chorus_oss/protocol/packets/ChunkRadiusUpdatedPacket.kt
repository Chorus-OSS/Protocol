package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int


data class ChunkRadiusUpdatedPacket(
    val radius: Int
) : Packet(id) {
    companion object : PacketCodec<ChunkRadiusUpdatedPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.CHUNK_RADIUS_UPDATED_PACKET

        override fun deserialize(stream: Source): ChunkRadiusUpdatedPacket {
            return ChunkRadiusUpdatedPacket(
                radius = ProtoVAR.Int.deserialize(stream)
            )
        }

        override fun serialize(value: ChunkRadiusUpdatedPacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.radius, stream)
        }
    }
}
