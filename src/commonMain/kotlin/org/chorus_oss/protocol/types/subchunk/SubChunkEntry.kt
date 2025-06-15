package org.chorus_oss.protocol.types.subchunk

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.ByteString
import org.chorus_oss.protocol.core.types.ULong

data class SubChunkEntry(
    val offset: SubChunkOffset,
    val resultType: ResultType,
    val rawPayload: ByteString?,
    val heightMapType: HeightMapType,
    val heightMapData: List<Byte>?,
    val blobHash: ULong?,
) {
    companion object : ProtoCodec<SubChunkEntry> {
        enum class ResultType(val net: Byte) {
            Success(1),
            ChunkNotFound(2),
            InvalidDimension(3),
            PlayerNotFound(4),
            IndexOutOfBounds(5),
            SuccessAllAir(6);

            companion object : ProtoCodec<ResultType> {
                override fun serialize(
                    value: ResultType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): ResultType {
                    return Proto.Byte.deserialize(stream).let {
                        entries.find { e -> e.net == it }!!
                    }
                }
            }
        }

        enum class HeightMapType {
            None,
            HasData,
            TooHigh,
            TooLow;

            companion object : ProtoCodec<HeightMapType> {
                override fun serialize(
                    value: HeightMapType,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): HeightMapType {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(value: SubChunkEntry, stream: Sink) {
            SubChunkOffset.serialize(value.offset, stream)
            ResultType.serialize(value.resultType, stream)
            when (value.resultType) {
                ResultType.SuccessAllAir -> Unit
                else -> Proto.ByteString.serialize(value.rawPayload as ByteString, stream)
            }
            HeightMapType.serialize(value.heightMapType, stream)
            when (value.heightMapType) {
                HeightMapType.HasData -> (value.heightMapData as List<Byte>).let {
                    for (i in 0 until 256) {
                        Proto.Byte.serialize(it.getOrElse(i) { 0 }, stream)
                    }
                }

                else -> Unit
            }
            ProtoVAR.ULong.serialize(value.blobHash as ULong, stream)
        }

        override fun deserialize(stream: Source): SubChunkEntry {
            val resultType: ResultType
            val heightMapType: HeightMapType
            return SubChunkEntry(
                offset = SubChunkOffset.deserialize(stream),
                resultType = ResultType.deserialize(stream).also { resultType = it },
                rawPayload = when (resultType) {
                    ResultType.SuccessAllAir -> null
                    else -> Proto.ByteString.deserialize(stream)
                },
                heightMapType = HeightMapType.deserialize(stream).also { heightMapType = it },
                heightMapData = when (heightMapType) {
                    HeightMapType.HasData -> List(256) {
                        Proto.Byte.deserialize(stream)
                    }

                    else -> null
                },
                blobHash = ProtoVAR.ULong.deserialize(stream),
            )
        }
    }
}

object SubChunkEntryNoCache : ProtoCodec<SubChunkEntry> {
    override fun serialize(value: SubChunkEntry, stream: Sink) {
        SubChunkOffset.serialize(value.offset, stream)
        SubChunkEntry.Companion.ResultType.serialize(value.resultType, stream)
        Proto.ByteString.serialize(value.rawPayload as ByteString, stream)
        SubChunkEntry.Companion.HeightMapType.serialize(value.heightMapType, stream)
        when (value.heightMapType) {
            SubChunkEntry.Companion.HeightMapType.HasData -> (value.heightMapData as List<Byte>).let {
                for (i in 0 until 256) {
                    Proto.Byte.serialize(it.getOrElse(i) { 0 }, stream)
                }
            }

            else -> Unit
        }
    }

    override fun deserialize(stream: Source): SubChunkEntry {
        val heightMapType: SubChunkEntry.Companion.HeightMapType
        return SubChunkEntry(
            offset = SubChunkOffset.deserialize(stream),
            resultType = SubChunkEntry.Companion.ResultType.deserialize(stream),
            rawPayload = Proto.ByteString.deserialize(stream),
            heightMapType = SubChunkEntry.Companion.HeightMapType.deserialize(stream).also { heightMapType = it },
            heightMapData = when (heightMapType) {
                SubChunkEntry.Companion.HeightMapType.HasData -> List(256) {
                    Proto.Byte.deserialize(stream)
                }

                else -> null
            },
            blobHash = null
        )
    }
}
