package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int

abstract class Recipe {
    abstract val type: Type

    companion object : ProtoCodec<Recipe> {
        enum class Type {
            Shapeless,
            Shaped,
            Furnace,
            FurnaceData,
            Multi,
            ShulkerBox,
            ShapelessChemistry,
            ShapedChemistry,
            SmithingTransform,
            SmithingTrim;

            companion object : ProtoCodec<Type> {
                override fun serialize(value: Type, stream: Sink) {
                    ProtoVAR.Int.serialize(value.ordinal, stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[ProtoVAR.Int.deserialize(stream)]
                }
            }
        }

        override fun serialize(value: Recipe, stream: Sink) {
            Type.serialize(value.type, stream)
            when (value) {
                is ShapelessRecipe -> ShapelessRecipe.serialize(value, stream)
                is ShapedRecipe -> ShapedRecipe.serialize(value, stream)
                is FurnaceRecipe -> FurnaceRecipe.serialize(value, stream)
                is FurnaceDataRecipe -> FurnaceDataRecipe.serialize(value, stream)
                is MultiRecipe -> MultiRecipe.serialize(value, stream)
                is ShulkerBoxRecipe -> ShulkerBoxRecipe.serialize(value, stream)
                is ShapelessChemistryRecipe -> ShapelessChemistryRecipe.serialize(value, stream)
                is ShapedChemistryRecipe -> ShapedChemistryRecipe.serialize(value, stream)
                is SmithingTransformRecipe -> SmithingTransformRecipe.serialize(value, stream)
                is SmithingTrimRecipe -> SmithingTrimRecipe.serialize(value, stream)
            }
        }

        override fun deserialize(stream: Source): Recipe {
            val type = Type.deserialize(stream)
            return when (type) {
                Type.Shapeless -> ShapelessRecipe.deserialize(stream)
                Type.Shaped -> ShapedRecipe.deserialize(stream)
                Type.Furnace -> FurnaceRecipe.deserialize(stream)
                Type.FurnaceData -> FurnaceDataRecipe.deserialize(stream)
                Type.Multi -> MultiRecipe.deserialize(stream)
                Type.ShulkerBox -> ShulkerBoxRecipe.deserialize(stream)
                Type.ShapelessChemistry -> ShapelessChemistryRecipe.deserialize(stream)
                Type.ShapedChemistry -> ShapedChemistryRecipe.deserialize(stream)
                Type.SmithingTransform -> SmithingTransformRecipe.deserialize(stream)
                Type.SmithingTrim -> SmithingTrimRecipe.deserialize(stream)
            }
        }
    }
}