package org.chorus_oss.protocol.types.subchunk

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

data class SubChunkOffset(
    val x: Byte,
    val y: Byte,
    val z: Byte,
) {
    companion object : ProtoCodec<SubChunkOffset> {
        override fun serialize(value: SubChunkOffset, stream: Sink) {
            Proto.Byte.serialize(value.x, stream)
            Proto.Byte.serialize(value.y, stream)
            Proto.Byte.serialize(value.z, stream)
        }

        override fun deserialize(stream: Source): SubChunkOffset {
            return SubChunkOffset(
                x = Proto.Byte.deserialize(stream),
                y = Proto.Byte.deserialize(stream),
                z = Proto.Byte.deserialize(stream),
            )
        }
    }
}
