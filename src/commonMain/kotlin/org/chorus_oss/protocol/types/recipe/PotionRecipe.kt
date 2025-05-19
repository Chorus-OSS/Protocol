package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class PotionRecipe(
    val inputPotionID: Int,
    val inputPotionMetadata: Int,
    val reagentItemID: Int,
    val reagentItemMetadata: Int,
    val outputPotionID: Int,
    val outputPotionMetadata: Int,
) {
    companion object : ProtoCodec<PotionRecipe> {
        override fun serialize(value: PotionRecipe, stream: Sink) {
            ProtoVAR.Int.serialize(value.inputPotionID, stream)
            ProtoVAR.Int.serialize(value.inputPotionMetadata, stream)
            ProtoVAR.Int.serialize(value.inputPotionID, stream)
            ProtoVAR.Int.serialize(value.reagentItemID, stream)
            ProtoVAR.Int.serialize(value.reagentItemMetadata, stream)
            ProtoVAR.Int.serialize(value.outputPotionID, stream)
            ProtoVAR.Int.serialize(value.outputPotionMetadata, stream)
        }

        override fun deserialize(stream: Source): PotionRecipe {
            return PotionRecipe(
                inputPotionID = ProtoVAR.Int.deserialize(stream),
                inputPotionMetadata = ProtoVAR.Int.deserialize(stream),
                reagentItemID = ProtoVAR.Int.deserialize(stream),
                reagentItemMetadata = ProtoVAR.Int.deserialize(stream),
                outputPotionID = ProtoVAR.Int.deserialize(stream),
                outputPotionMetadata = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
