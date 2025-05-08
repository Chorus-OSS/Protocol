package org.chorus_oss.chorus.network.protocol

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf
import org.chorus_oss.chorus.network.protocol.types.biome.BiomeDefinitionData

data class BiomeDefinitionListPacket(
    val biomeDefinitions: Map<Short, BiomeDefinitionData>,
    val biomeStringList: List<String>,
) : DataPacket(), PacketEncoder {
    override fun pid(): Int {
        return ProtocolInfo.BIOME_DEFINITION_LIST_PACKET
    }

    override fun handle(handler: PacketHandler) {}

    override fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeUnsignedVarInt(biomeDefinitions.size)
        for ((key, value) in biomeDefinitions) {
            byteBuf.writeShortLE(key.toInt())
            value.encode(byteBuf)
        }
        byteBuf.writeArray(biomeStringList) { buf, data ->
            buf.writeString(data)
        }
    }
}
