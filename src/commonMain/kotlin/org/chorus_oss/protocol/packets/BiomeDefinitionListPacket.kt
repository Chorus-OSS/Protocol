package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Short
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.biome.BiomeDefinitionData

data class BiomeDefinitionListPacket(
    val biomeDefinitions: Map<Short, BiomeDefinitionData>,
    val biomeStringList: List<String>,
) : Packet(id) {
    companion object : PacketCodec<BiomeDefinitionListPacket> {
        override val id: Int = 122

        override fun deserialize(stream: Source): BiomeDefinitionListPacket {
            return BiomeDefinitionListPacket(
                biomeDefinitions = ProtoHelper.deserializeList(stream) { buf ->
                    Pair(
                        ProtoLE.Short.deserialize(buf),
                        BiomeDefinitionData.deserialize(buf)
                    )
                }.toMap(),
                biomeStringList = ProtoHelper.deserializeList(stream, Proto.String)
            )
        }

        override fun serialize(value: BiomeDefinitionListPacket, stream: Sink) {
            ProtoHelper.serializeList(value.biomeDefinitions.entries.toList(), stream) { (k, v), buf ->
                ProtoLE.Short.serialize(k, buf)
                BiomeDefinitionData.serialize(v, buf)
            }
            ProtoHelper.serializeList(value.biomeStringList, stream, Proto.String)
        }
    }
}
