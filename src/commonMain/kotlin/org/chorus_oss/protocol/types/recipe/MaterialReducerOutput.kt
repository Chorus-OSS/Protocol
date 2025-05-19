package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class MaterialReducerOutput(
    val networkID: Int,
    val count: Int,
) {
    companion object : ProtoCodec<MaterialReducerOutput> {
        override fun serialize(
            value: MaterialReducerOutput,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.networkID, stream)
            ProtoVAR.Int.serialize(value.count, stream)
        }

        override fun deserialize(stream: Source): MaterialReducerOutput {
            return MaterialReducerOutput(
                networkID = ProtoVAR.Int.deserialize(stream),
                count = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
