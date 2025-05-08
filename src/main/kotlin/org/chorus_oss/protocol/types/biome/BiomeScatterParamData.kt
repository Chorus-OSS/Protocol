package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeScatterParamData(
    val coordinate: List<BiomeCoordinateData>,
    val evalOrder: CoordinateEvaluationOrder,
    val chancePercentType: ExpressionOp,
    val chancePercent: Short,
    val chanceNumerator: Int,
    val chanceDenominator: Int,
    val iterationsType: ExpressionOp,
    val iterations: Short,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeArray(coordinate) { buf, data ->
            data.encode(buf)
        }
        byteBuf.writeVarInt(evalOrder.ordinal)
        byteBuf.writeVarInt(chancePercentType.ordinal)
        byteBuf.writeShortLE(chancePercent.toInt())
        byteBuf.writeIntLE(chanceNumerator)
        byteBuf.writeIntLE(chanceDenominator)
        byteBuf.writeVarInt(iterationsType.ordinal)
        byteBuf.writeShortLE(iterations.toInt())
    }
}
