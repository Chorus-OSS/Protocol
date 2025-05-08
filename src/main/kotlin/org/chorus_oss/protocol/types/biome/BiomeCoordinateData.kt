package org.chorus_oss.chorus.network.protocol.types.biome

import org.chorus_oss.chorus.network.connection.util.HandleByteBuf

data class BiomeCoordinateData(
    val minValueType: ExpressionOp,
    val minValue: Short,
    val maxValueType: ExpressionOp,
    val maxValue: Short,
    val gridOffset: UInt,
    val gridStepSize: UInt,
    val distribution: RandomDistributionType,
) {
    fun encode(byteBuf: HandleByteBuf) {
        byteBuf.writeVarInt(minValueType.ordinal)
        byteBuf.writeShortLE(minValue.toInt())
        byteBuf.writeVarInt(maxValueType.ordinal)
        byteBuf.writeShortLE(maxValue.toInt())
        byteBuf.writeUnsignedVarInt(gridOffset.toInt())
        byteBuf.writeUnsignedVarInt(gridStepSize.toInt())
        byteBuf.writeVarInt(distribution.ordinal)
    }
}
