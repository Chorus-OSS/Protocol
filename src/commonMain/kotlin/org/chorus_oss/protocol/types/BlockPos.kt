package org.chorus_oss.protocol.types

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt

data class BlockPos(
    val x: Int,
    val y: Int,
    val z: Int
) {
    companion object : ProtoCodec<BlockPos> {
        override fun serialize(value: BlockPos, stream: Sink) {
            ProtoVAR.Int.serialize(value.x, stream)
            ProtoVAR.Int.serialize(value.y, stream)
            ProtoVAR.Int.serialize(value.z, stream)
        }

        override fun deserialize(stream: Source): BlockPos {
            return BlockPos(
                x = ProtoVAR.Int.deserialize(stream),
                y = ProtoVAR.Int.deserialize(stream),
                z = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}

object NetBlockPos : ProtoCodec<BlockPos> {
    override fun serialize(value: BlockPos, stream: Sink) {
        ProtoVAR.Int.serialize(value.x, stream)
        ProtoVAR.UInt.serialize(value.y.toUInt(), stream)
        ProtoVAR.Int.serialize(value.z, stream)
    }

    override fun deserialize(stream: Source): BlockPos {
        return BlockPos(
            x = ProtoVAR.Int.deserialize(stream),
            y = ProtoVAR.UInt.deserialize(stream).toInt(),
            z = ProtoVAR.Int.deserialize(stream),
        )
    }
}
