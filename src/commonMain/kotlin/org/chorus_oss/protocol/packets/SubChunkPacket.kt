package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.BlockPos
import org.chorus_oss.protocol.types.subchunk.SubChunkEntry
import org.chorus_oss.protocol.types.subchunk.SubChunkEntryNoCache

data class SubChunkPacket(
    val cacheEnabled: Boolean,
    val dimension: Int,
    val position: BlockPos,
    val subChunkEntries: List<SubChunkEntry>,
) : Packet(id) {
    companion object : PacketCodec<SubChunkPacket> {
        override val id: Int = 174

        override fun serialize(value: SubChunkPacket, stream: Sink) {
            Proto.Boolean.serialize(value.cacheEnabled, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
            BlockPos.serialize(value.position, stream)
            value.subChunkEntries.let { subChunkEntries ->
                ProtoLE.UInt.serialize(subChunkEntries.size.toUInt(), stream)
                when (value.cacheEnabled) {
                    true -> subChunkEntries.forEach { SubChunkEntry.serialize(it, stream) }
                    false -> subChunkEntries.forEach { SubChunkEntryNoCache.serialize(it, stream) }
                }
            }
        }

        override fun deserialize(stream: Source): SubChunkPacket {
            val cacheEnabled: Boolean
            return SubChunkPacket(
                cacheEnabled = Proto.Boolean.deserialize(stream).also { cacheEnabled = it },
                dimension = ProtoVAR.Int.deserialize(stream),
                position = BlockPos.deserialize(stream),
                subChunkEntries = ProtoLE.UInt.deserialize(stream).let {
                    when (cacheEnabled) {
                        true -> List(it.toInt()) { SubChunkEntry.deserialize(stream) }
                        false -> List(it.toInt()) { SubChunkEntryNoCache.deserialize(stream) }
                    }
                }
            )
        }
    }
}
