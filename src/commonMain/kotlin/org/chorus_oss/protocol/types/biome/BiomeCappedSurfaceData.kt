package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UInt

data class BiomeCappedSurfaceData(
    val floorBlocks: List<Int>,
    val ceilingBlocks: List<Int>,
    val seaBlock: UInt? = null,
    val foundationBlock: UInt? = null,
    val beachBlock: UInt? = null,
) {
    companion object : ProtoCodec<BiomeCappedSurfaceData> {
        override fun serialize(value: BiomeCappedSurfaceData, stream: Sink) {
            ProtoHelper.serializeList(value.floorBlocks, stream, ProtoLE.Int::serialize)
            ProtoHelper.serializeList(value.ceilingBlocks, stream, ProtoLE.Int::serialize)
            ProtoHelper.serializeNullable(value.seaBlock, stream, ProtoLE.UInt::serialize)
            ProtoHelper.serializeNullable(value.foundationBlock, stream, ProtoLE.UInt::serialize)
            ProtoHelper.serializeNullable(value.beachBlock, stream, ProtoLE.UInt::serialize)
        }

        override fun deserialize(stream: Source): BiomeCappedSurfaceData {
            return BiomeCappedSurfaceData(
                floorBlocks = ProtoHelper.deserializeList(stream, ProtoLE.Int::deserialize),
                ceilingBlocks = ProtoHelper.deserializeList(stream, ProtoLE.Int::deserialize),
                seaBlock = ProtoHelper.deserializeNullable(stream, ProtoLE.UInt::deserialize),
                foundationBlock = ProtoHelper.deserializeNullable(stream, ProtoLE.UInt::deserialize),
                beachBlock = ProtoHelper.deserializeNullable(stream, ProtoLE.UInt::deserialize),
            )
        }
    }
}
