package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int

data class BiomeSurfaceMaterialData(
    val topBlock: Int,
    val midBlock: Int,
    val seaFloorBlock: Int,
    val foundationBlock: Int,
    val seaBlock: Int,
    val seaFloorDepth: Int,
) {
    companion object : ProtoCodec<BiomeSurfaceMaterialData> {
        override fun serialize(value: BiomeSurfaceMaterialData, stream: Sink) {
            ProtoLE.Int.serialize(value.topBlock, stream)
            ProtoLE.Int.serialize(value.midBlock, stream)
            ProtoLE.Int.serialize(value.seaFloorBlock, stream)
            ProtoLE.Int.serialize(value.seaBlock, stream)
            ProtoLE.Int.serialize(value.seaFloorDepth, stream)
            ProtoLE.Int.serialize(value.foundationBlock, stream)
        }

        override fun deserialize(stream: Source): BiomeSurfaceMaterialData {
            return BiomeSurfaceMaterialData(
                topBlock = ProtoLE.Int.deserialize(stream),
                midBlock = ProtoLE.Int.deserialize(stream),
                seaFloorBlock = ProtoLE.Int.deserialize(stream),
                seaBlock = ProtoLE.Int.deserialize(stream),
                seaFloorDepth = ProtoLE.Int.deserialize(stream),
                foundationBlock = ProtoLE.Int.deserialize(stream),
            )
        }
    }
}
