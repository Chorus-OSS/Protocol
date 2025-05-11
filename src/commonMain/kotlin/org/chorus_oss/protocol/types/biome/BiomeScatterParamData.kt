package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.Short

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
    companion object : ProtoCodec<BiomeScatterParamData> {
        override fun serialize(value: BiomeScatterParamData, stream: Sink) {
            ProtoHelper.serializeList(value.coordinate, stream, BiomeCoordinateData::serialize)
            CoordinateEvaluationOrder.serialize(value.evalOrder, stream)
            ExpressionOp.serialize(value.chancePercentType, stream)
            ProtoLE.Short.serialize(value.chancePercent, stream)
            ProtoLE.Int.serialize(value.chanceNumerator, stream)
            ProtoLE.Int.serialize(value.chanceDenominator, stream)
            ExpressionOp.serialize(value.iterationsType, stream)
            ProtoLE.Short.serialize(value.iterations, stream)
        }

        override fun deserialize(stream: Source): BiomeScatterParamData {
            return BiomeScatterParamData(
                coordinate = ProtoHelper.deserializeList(stream, BiomeCoordinateData::deserialize),
                evalOrder = CoordinateEvaluationOrder.deserialize(stream),
                chancePercentType = ExpressionOp.deserialize(stream),
                chancePercent = ProtoLE.Short.deserialize(stream),
                chanceNumerator = ProtoLE.Int.deserialize(stream),
                chanceDenominator = ProtoLE.Int.deserialize(stream),
                iterationsType = ExpressionOp.deserialize(stream),
                iterations = ProtoLE.Short.deserialize(stream),
            )
        }

    }
}
