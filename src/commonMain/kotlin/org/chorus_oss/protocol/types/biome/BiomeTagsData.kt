package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeTagsData(
    val tags: List<Short>
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeArray(tags) { buf, tag ->
            buf.writeShortLE(tag.toInt())
        }
    }
}
