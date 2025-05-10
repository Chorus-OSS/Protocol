package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.UInt

data class BiomeMesaSurfaceData(
    val clayMaterial: UInt,
    val hardClayMaterial: UInt,
    val brycePillars: Boolean,
    val hasForest: Boolean,
) {
    companion object : ProtoCodec<BiomeMesaSurfaceData> {
        override fun serialize(value: BiomeMesaSurfaceData, stream: Buffer) {
            ProtoLE.UInt.serialize(value.clayMaterial, stream)
            ProtoLE.UInt.serialize(value.hardClayMaterial, stream)
            Proto.Boolean.serialize(value.brycePillars, stream)
            Proto.Boolean.serialize(value.hasForest, stream)
        }

        override fun deserialize(stream: Buffer): BiomeMesaSurfaceData {
            return BiomeMesaSurfaceData(
                clayMaterial = ProtoLE.UInt.deserialize(stream),
                hardClayMaterial = ProtoLE.UInt.deserialize(stream),
                brycePillars = Proto.Boolean.deserialize(stream),
                hasForest = Proto.Boolean.deserialize(stream)
            )
        }
    }
}
