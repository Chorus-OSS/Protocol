package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class ChunkPos(
    val x: Int,
    val z: Int,
) {
    companion object : ProtoCodec<ChunkPos> {
        override fun serialize(value: ChunkPos, stream: Sink) {
            ProtoVAR.Int.serialize(value.x, stream)
            ProtoVAR.Int.serialize(value.z, stream)
        }

        override fun deserialize(stream: Source): ChunkPos {
            return ChunkPos(
                x = ProtoVAR.Int.deserialize(stream),
                z = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
