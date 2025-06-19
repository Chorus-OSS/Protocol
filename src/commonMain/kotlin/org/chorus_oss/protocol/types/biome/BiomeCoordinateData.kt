package org.chorus_oss.protocol.types.biome

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Short
import org.chorus_oss.protocol.core.types.UInt

data class BiomeCoordinateData(
    val minValueType: ExpressionOp,
    val minValue: Short,
    val maxValueType: ExpressionOp,
    val maxValue: Short,
    val gridOffset: UInt,
    val gridStepSize: UInt,
    val distribution: RandomDistributionType,
) {
    companion object : ProtoCodec<BiomeCoordinateData> {
        override fun serialize(value: BiomeCoordinateData, stream: Sink) {
            ExpressionOp.serialize(value.minValueType, stream)
            ProtoLE.Short.serialize(value.minValue, stream)
            ExpressionOp.serialize(value.maxValueType, stream)
            ProtoLE.Short.serialize(value.maxValue, stream)
            ProtoLE.UInt.serialize(value.gridOffset, stream)
            ProtoLE.UInt.serialize(value.gridStepSize, stream)
            RandomDistributionType.serialize(value.distribution, stream)
        }

        override fun deserialize(stream: Source): BiomeCoordinateData {
            return BiomeCoordinateData(
                minValueType = ExpressionOp.deserialize(stream),
                minValue = ProtoLE.Short.deserialize(stream),
                maxValueType = ExpressionOp.deserialize(stream),
                maxValue = ProtoLE.Short.deserialize(stream),
                gridOffset = ProtoLE.UInt.deserialize(stream),
                gridStepSize = ProtoLE.UInt.deserialize(stream),
                distribution = RandomDistributionType.deserialize(stream)
            )
        }
    }
}
