package org.chorus_oss.protocol.types.recipe

data class ShapelessRecipe(
    val recipeID: String,
    val input: List<ItemDescriptorCount>
)
