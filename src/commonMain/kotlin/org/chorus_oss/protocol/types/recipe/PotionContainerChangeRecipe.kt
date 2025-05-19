package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

data class PotionContainerChangeRecipe(
    val inputItemID: Int,
    val reagentItemID: Int,
    val outputItemID: Int,
) {
    companion object : ProtoCodec<PotionContainerChangeRecipe> {
        override fun serialize(
            value: PotionContainerChangeRecipe,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.inputItemID, stream)
            ProtoVAR.Int.serialize(value.reagentItemID, stream)
            ProtoVAR.Int.serialize(value.outputItemID, stream)
        }

        override fun deserialize(stream: Source): PotionContainerChangeRecipe {
            return PotionContainerChangeRecipe(
                inputItemID = ProtoVAR.Int.deserialize(stream),
                reagentItemID = ProtoVAR.Int.deserialize(stream),
                outputItemID = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
