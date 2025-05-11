package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Int

data class BiomeMountainsParamData(
    val steepBlock: Int,
    val northSlopes: Boolean,
    val southSlopes: Boolean,
    val westSlopes: Boolean,
    val eastSlopes: Boolean,
    val topSlideEnabled: Boolean,
) {
    companion object : ProtoCodec<BiomeMountainsParamData> {
        override fun serialize(value: BiomeMountainsParamData, stream: Sink) {
            ProtoLE.Int.serialize(value.steepBlock, stream)
            Proto.Boolean.serialize(value.northSlopes, stream)
            Proto.Boolean.serialize(value.southSlopes, stream)
            Proto.Boolean.serialize(value.westSlopes, stream)
            Proto.Boolean.serialize(value.eastSlopes, stream)
            Proto.Boolean.serialize(value.topSlideEnabled, stream)
        }

        override fun deserialize(stream: Source): BiomeMountainsParamData {
            return BiomeMountainsParamData(
                steepBlock = ProtoLE.Int.deserialize(stream),
                northSlopes = Proto.Boolean.deserialize(stream),
                southSlopes = Proto.Boolean.deserialize(stream),
                westSlopes = Proto.Boolean.deserialize(stream),
                eastSlopes = Proto.Boolean.deserialize(stream),
                topSlideEnabled = Proto.Boolean.deserialize(stream),
            )
        }
    }
}
