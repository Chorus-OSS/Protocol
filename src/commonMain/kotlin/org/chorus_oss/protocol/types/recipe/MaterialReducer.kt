package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class MaterialReducer(
    val itemKey: Int,
    val outputs: List<MaterialReducerOutput>
) {
    companion object : ProtoCodec<MaterialReducer> {
        override fun serialize(value: MaterialReducer, stream: Sink) {
            ProtoVAR.Int.serialize(value.itemKey, stream)
            ProtoHelper.serializeList(value.outputs, stream, MaterialReducerOutput)
        }

        override fun deserialize(stream: Source): MaterialReducer {
            return MaterialReducer(
                itemKey = ProtoVAR.Int.deserialize(stream),
                outputs = ProtoHelper.deserializeList(stream, MaterialReducerOutput)
            )
        }
    }
}