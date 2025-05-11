package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.*

data class BiomeDefinitionData(
    val id: UShort? = null,
    val temperature: Float,
    val downfall: Float,
    val redSporeDensity: Float,
    val blueSporeDensity: Float,
    val ashDensity: Float,
    val whiteAshDensity: Float,
    val depth: Float,
    val scale: Float,
    val mapWaterColorARGB: Int,
    val rain: Boolean,
    val tags: BiomeTagsData? = null,
    val chunkGenData: BiomeDefinitionChunkGenData? = null
) {
    companion object : ProtoCodec<BiomeDefinitionData> {
        override fun serialize(value: BiomeDefinitionData, stream: Sink) {
            ProtoHelper.serializeNullable(value.id, stream, ProtoLE.UShort::serialize)
            ProtoLE.Float.serialize(value.temperature, stream)
            ProtoLE.Float.serialize(value.downfall, stream)
            ProtoLE.Float.serialize(value.redSporeDensity, stream)
            ProtoLE.Float.serialize(value.blueSporeDensity, stream)
            ProtoLE.Float.serialize(value.ashDensity, stream)
            ProtoLE.Float.serialize(value.whiteAshDensity, stream)
            ProtoLE.Float.serialize(value.depth, stream)
            ProtoLE.Float.serialize(value.scale, stream)
            ProtoLE.Int.serialize(value.mapWaterColorARGB, stream)
            Proto.Boolean.serialize(value.rain, stream)
            ProtoHelper.serializeNullable(value.tags, stream, BiomeTagsData::serialize)
            ProtoHelper.serializeNullable(value.chunkGenData, stream, BiomeDefinitionChunkGenData::serialize)
        }

        override fun deserialize(stream: Source): BiomeDefinitionData {
            return BiomeDefinitionData(
                id = ProtoHelper.deserializeNullable(stream, ProtoLE.UShort::deserialize),
                temperature = ProtoLE.Float.deserialize(stream),
                downfall = ProtoLE.Float.deserialize(stream),
                redSporeDensity = ProtoLE.Float.deserialize(stream),
                blueSporeDensity = ProtoLE.Float.deserialize(stream),
                ashDensity = ProtoLE.Float.deserialize(stream),
                whiteAshDensity = ProtoLE.Float.deserialize(stream),
                depth = ProtoLE.Float.deserialize(stream),
                scale = ProtoLE.Float.deserialize(stream),
                mapWaterColorARGB = ProtoLE.Int.deserialize(stream),
                rain = Proto.Boolean.deserialize(stream),
                tags = ProtoHelper.deserializeNullable(stream, BiomeTagsData::deserialize),
                chunkGenData = ProtoHelper.deserializeNullable(stream, BiomeDefinitionChunkGenData::deserialize)
            )
        }
    }
}
