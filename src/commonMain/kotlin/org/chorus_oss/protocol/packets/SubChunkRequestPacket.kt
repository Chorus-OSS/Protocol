package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.subchunk.SubChunkOffset

data class SubChunkRequestPacket(
    val dimension: Int,
    val position: BlockPos,
    val offsets: List<SubChunkOffset>,
) : Packet(id) {
    companion object : PacketCodec<SubChunkRequestPacket> {
        override val id: Int = 175

        override fun serialize(value: SubChunkRequestPacket, stream: Sink) {
            ProtoVAR.Int.serialize(value.dimension, stream)
            BlockPos.serialize(value.position, stream)
            value.offsets.let { offsets ->
                ProtoLE.UInt.serialize(offsets.size.toUInt(), stream)
                offsets.forEach { SubChunkOffset.serialize(it, stream) }
            }
        }

        override fun deserialize(stream: Source): SubChunkRequestPacket {
            return SubChunkRequestPacket(
                dimension = ProtoVAR.Int.deserialize(stream),
                position = BlockPos.deserialize(stream),
                offsets = List(ProtoLE.UInt.deserialize(stream).toInt()) {
                    SubChunkOffset.deserialize(stream)
                }
            )
        }
    }
}
