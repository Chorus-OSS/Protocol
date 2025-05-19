package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.item.ItemInstance

data class FurnaceDataRecipe(
    val itemNetworkID: Int,
    val itemAux: Int,
    val output: ItemInstance,
    val block: String,
) : Recipe() {
    override val type: Recipe.Companion.Type
        get() = Recipe.Companion.Type.FurnaceData

    companion object : ProtoCodec<FurnaceDataRecipe> {
        override fun serialize(value: FurnaceDataRecipe, stream: Sink) {
            ProtoVAR.Int.serialize(value.itemNetworkID, stream)
            ProtoVAR.Int.serialize(value.itemAux, stream)
            ItemInstance.serialize(value.output, stream)
            Proto.String.serialize(value.block, stream)
        }

        override fun deserialize(stream: Source): FurnaceDataRecipe {
            return FurnaceDataRecipe(
                itemNetworkID = ProtoVAR.Int.deserialize(stream),
                itemAux = ProtoVAR.Int.deserialize(stream),
                output = ItemInstance.deserialize(stream),
                block = Proto.String.deserialize(stream),
            )
        }
    }
}
