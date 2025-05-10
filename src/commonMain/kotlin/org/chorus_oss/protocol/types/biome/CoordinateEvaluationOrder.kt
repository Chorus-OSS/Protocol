package org.chorus_oss.protocol.types.biome

import kotlinx.io.Buffer
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

enum class CoordinateEvaluationOrder {
    XYZ,
    XZY,
    YXZ,
    YZX,
    ZXY,
    ZYX;

    companion object : ProtoCodec<CoordinateEvaluationOrder> {
        override fun serialize(value: CoordinateEvaluationOrder, stream: Buffer) {
            ProtoVAR.Int.serialize(value.ordinal, stream)
        }

        override fun deserialize(stream: Buffer): CoordinateEvaluationOrder {
            return entries[ProtoVAR.Int.deserialize(stream)]
        }
    }
}