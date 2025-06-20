package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.*
import org.chorus_oss.protocol.types.ChunkPos


data class LevelChunkPacket(
    val position: ChunkPos,
    val dimension: Int,
    val subChunkCount: UInt,
    val subChunkLimit: UShort,
    val cacheEnabled: Boolean,
    val blobHashes: List<ULong>,
    val data: ByteString
) : Packet(id) {
    companion object : PacketCodec<LevelChunkPacket> {
        val LIMITLESS: UInt = UInt.MAX_VALUE
        val LIMITED: UInt = UInt.MAX_VALUE - 1u

        override val id: Int = 58

        override fun serialize(value: LevelChunkPacket, stream: Sink) {
            ChunkPos.serialize(value.position, stream)
            ProtoVAR.Int.serialize(value.dimension, stream)
            ProtoVAR.UInt.serialize(value.subChunkCount, stream)
            when (value.subChunkCount == LIMITED) {
                true -> ProtoLE.UShort.serialize(value.subChunkLimit, stream)
                false -> Unit
            }
            Proto.Boolean.serialize(value.cacheEnabled, stream)
            when (value.cacheEnabled) {
                true -> ProtoHelper.serializeList(value.blobHashes, stream, ProtoLE.ULong)
                false -> Unit
            }
            Proto.ByteString.serialize(value.data, stream)
        }

        override fun deserialize(stream: Source): LevelChunkPacket {
            val subChunkCount: UInt
            val cacheEnabled: Boolean
            return LevelChunkPacket(
                position = ChunkPos.deserialize(stream),
                dimension = ProtoVAR.Int.deserialize(stream),
                subChunkCount = ProtoVAR.UInt.deserialize(stream).also { subChunkCount = it },
                subChunkLimit = when (subChunkCount == LIMITED) {
                    true -> ProtoLE.UShort.deserialize(stream)
                    false -> 0u
                },
                cacheEnabled = Proto.Boolean.deserialize(stream).also { cacheEnabled = it },
                blobHashes = when (cacheEnabled) {
                    true -> ProtoHelper.deserializeList(stream, ProtoLE.ULong)
                    false -> emptyList()
                },
                data = Proto.ByteString.deserialize(stream),
            )
        }
    }
}
