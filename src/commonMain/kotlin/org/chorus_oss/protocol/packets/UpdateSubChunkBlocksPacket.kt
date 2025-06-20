package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.BlockChangeEntry
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.NetBlockPos


data class UpdateSubChunkBlocksPacket(
    val position: BlockPos,
    val blocks: List<BlockChangeEntry>,
    val extra: List<BlockChangeEntry>,
) : Packet(id) {
    companion object : PacketCodec<UpdateSubChunkBlocksPacket> {
        override val id: Int = 172

        override fun serialize(
            value: UpdateSubChunkBlocksPacket,
            stream: Sink
        ) {
            NetBlockPos.serialize(value.position, stream)
            ProtoHelper.serializeList(value.blocks, stream, BlockChangeEntry)
            ProtoHelper.serializeList(value.extra, stream, BlockChangeEntry)
        }

        override fun deserialize(stream: Source): UpdateSubChunkBlocksPacket {
            return UpdateSubChunkBlocksPacket(
                position = NetBlockPos.deserialize(stream),
                blocks = ProtoHelper.deserializeList(stream, BlockChangeEntry),
                extra = ProtoHelper.deserializeList(stream, BlockChangeEntry)
            )
        }
    }
}
