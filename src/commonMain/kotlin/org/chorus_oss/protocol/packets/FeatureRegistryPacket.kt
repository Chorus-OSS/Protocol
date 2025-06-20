package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.GenerationFeature

data class FeatureRegistryPacket(
    val features: List<GenerationFeature>,
) : Packet(id) {
    companion object : PacketCodec<FeatureRegistryPacket> {
        override val id: Int = 191

        override fun serialize(value: FeatureRegistryPacket, stream: Sink) {
            ProtoHelper.serializeList(value.features, stream, GenerationFeature)
        }

        override fun deserialize(stream: Source): FeatureRegistryPacket {
            return FeatureRegistryPacket(
                features = ProtoHelper.deserializeList(stream, GenerationFeature),
            )
        }
    }
}
