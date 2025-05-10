package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Short

data class BiomeConsolidatedFeatureData(
    val scatter: BiomeScatterParamData,
    val feature: Short,
    val identifier: Short,
    val pass: Short,
    val canUseInternal: Boolean,
) {
    companion object : ProtoCodec<BiomeConsolidatedFeatureData> {
        override fun serialize(value: BiomeConsolidatedFeatureData, stream: Buffer) {
            BiomeScatterParamData.serialize(value.scatter, stream)
            ProtoLE.Short.serialize(value.feature, stream)
            ProtoLE.Short.serialize(value.identifier, stream)
            ProtoLE.Short.serialize(value.pass, stream)
            Proto.Boolean.serialize(value.canUseInternal, stream)
        }

        override fun deserialize(stream: Buffer): BiomeConsolidatedFeatureData {
            return BiomeConsolidatedFeatureData(
                scatter = BiomeScatterParamData.deserialize(stream),
                feature = ProtoLE.Short.deserialize(stream),
                identifier = ProtoLE.Short.deserialize(stream),
                pass = ProtoLE.Short.deserialize(stream),
                canUseInternal = Proto.Boolean.deserialize(stream)
            )
        }

    }
}
