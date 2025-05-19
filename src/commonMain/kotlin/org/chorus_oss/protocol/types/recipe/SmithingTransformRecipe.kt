package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.types.item.ItemInstance
import org.chorus_oss.protocol.types.item.desciptor.ItemDescriptorCount

data class SmithingTransformRecipe(
    val recipeID: String,
    val template: ItemDescriptorCount,
    val base: ItemDescriptorCount,
    val addition: ItemDescriptorCount,
    val result: ItemInstance,
    val block: String,
    val recipeNetworkID: UInt,
) : Recipe() {
    override val type: Recipe.Companion.Type
        get() = Recipe.Companion.Type.SmithingTransform

    companion object : ProtoCodec<SmithingTransformRecipe> {
        override fun serialize(
            value: SmithingTransformRecipe,
            stream: Sink
        ) {
            Proto.String.serialize(value.recipeID, stream)
            ItemDescriptorCount.serialize(value.template, stream)
            ItemDescriptorCount.serialize(value.base, stream)
            ItemDescriptorCount.serialize(value.addition, stream)
            ItemInstance.serialize(value.result, stream)
            Proto.String.serialize(value.block, stream)
            ProtoVAR.UInt.serialize(value.recipeNetworkID, stream)
        }

        override fun deserialize(stream: Source): SmithingTransformRecipe {
            return SmithingTransformRecipe(
                recipeID = Proto.String.deserialize(stream),
                template = ItemDescriptorCount.deserialize(stream),
                base = ItemDescriptorCount.deserialize(stream),
                addition = ItemDescriptorCount.deserialize(stream),
                result = ItemInstance.deserialize(stream),
                block = Proto.String.deserialize(stream),
                recipeNetworkID = ProtoVAR.UInt.deserialize(stream),
            )
        }
    }
}
