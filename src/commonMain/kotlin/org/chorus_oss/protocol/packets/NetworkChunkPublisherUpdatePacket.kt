package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.ChunkPos
import org.chorus_oss.protocol.types.BlockPos


data class NetworkChunkPublisherUpdatePacket(
    val position: BlockPos,
    val radius: UInt,
    val savedChunks: List<ChunkPos>
) : Packet(id) {
    companion object : PacketCodec<NetworkChunkPublisherUpdatePacket> {
        override val id: Int = 121

        override fun serialize(
            value: NetworkChunkPublisherUpdatePacket,
            stream: Sink
        ) {
            BlockPos.serialize(value.position, stream)
            ProtoVAR.UInt.serialize(value.radius, stream)
            ProtoLE.Int.serialize(value.savedChunks.size, stream)
            value.savedChunks.forEach { ChunkPos.serialize(it, stream) }
        }

        override fun deserialize(stream: Source): NetworkChunkPublisherUpdatePacket {
            return NetworkChunkPublisherUpdatePacket(
                position = BlockPos.deserialize(stream),
                radius = ProtoVAR.UInt.deserialize(stream),
                savedChunks = List(ProtoLE.Int.deserialize(stream)) {
                    ChunkPos.deserialize(stream)
                }
            )
        }
    }
}
