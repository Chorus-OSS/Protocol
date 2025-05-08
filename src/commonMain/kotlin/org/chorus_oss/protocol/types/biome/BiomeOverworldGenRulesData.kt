package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeOverworldGenRulesData(
    val hillsTransformations: List<BiomeWeightedData>,
    val mutateTransformations: List<BiomeWeightedData>,
    val riverTransformations: List<BiomeWeightedData>,
    val shoreTransformations: List<BiomeWeightedData>,
    val preHillsEdge: List<BiomeConditionalTransformationData>,
    val postShoreEdge: List<BiomeConditionalTransformationData>,
    val climate: List<BiomeWeightedTemperatureData>,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeArray(hillsTransformations) { buf, data ->
            data.encode(buf)
        }
        byteBuf.writeArray(mutateTransformations) { buf, data ->
            data.encode(buf)
        }
        byteBuf.writeArray(riverTransformations) { buf, data ->
            data.encode(buf)
        }
        byteBuf.writeArray(shoreTransformations) { buf, data ->
            data.encode(buf)
        }
        byteBuf.writeArray(preHillsEdge) { buf, data ->
            data.encode(buf)
        }
        byteBuf.writeArray(postShoreEdge) { buf, data ->
            data.encode(buf)
        }
        byteBuf.writeArray(climate) { buf, data ->
            data.encode(buf)
        }
    }
}
