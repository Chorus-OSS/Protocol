package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.Uuid
import org.chorus_oss.protocol.types.item.ItemInstance
import org.chorus_oss.protocol.types.item.desciptor.InvalidItemDescriptor
import org.chorus_oss.protocol.types.item.desciptor.ItemDescriptorCount
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ShapedChemistryRecipe(
    val recipeID: String,
    val width: Int,
    val height: Int,
    val input: List<ItemDescriptorCount>,
    val output: List<ItemInstance>,
    val uuid: Uuid,
    val block: String,
    val priority: Int,
    val assumeSymmetry: Boolean,
    val unlockingRequirement: RecipeUnlockingRequirement,
    val recipeNetworkID: UInt,
) : Recipe() {
    override val type: Recipe.Companion.Type
        get() = Recipe.Companion.Type.ShapedChemistry

    companion object : ProtoCodec<ShapedChemistryRecipe> {
        override fun serialize(value: ShapedChemistryRecipe, stream: Sink) {
            Proto.String.serialize(value.recipeID, stream)
            ProtoVAR.Int.serialize(value.width, stream)
            ProtoVAR.Int.serialize(value.height, stream)
            for (i in 0 until value.width * value.height) {
                value.input.getOrElse(i) { ItemDescriptorCount(InvalidItemDescriptor(), 0) }.let {
                    ItemDescriptorCount.serialize(it, stream)
                }
            }
            ProtoHelper.serializeList(value.output, stream, ItemInstance)
            Proto.Uuid.serialize(value.uuid, stream)
            Proto.String.serialize(value.block, stream)
            ProtoVAR.Int.serialize(value.priority, stream)
            Proto.Boolean.serialize(value.assumeSymmetry, stream)
            RecipeUnlockingRequirement.serialize(value.unlockingRequirement, stream)
            ProtoVAR.UInt.serialize(value.recipeNetworkID, stream)
        }

        override fun deserialize(stream: Source): ShapedChemistryRecipe {
            val width: Int
            val height: Int
            return ShapedChemistryRecipe(
                recipeID = Proto.String.deserialize(stream),
                width = ProtoVAR.Int.deserialize(stream).also { width = it },
                height = ProtoVAR.Int.deserialize(stream).also { height = it },
                input = List(width * height) { ItemDescriptorCount.deserialize(stream) },
                output = ProtoHelper.deserializeList(stream, ItemInstance),
                uuid = Proto.Uuid.deserialize(stream),
                block = Proto.String.deserialize(stream),
                priority = ProtoVAR.Int.deserialize(stream),
                assumeSymmetry = Proto.Boolean.deserialize(stream),
                unlockingRequirement = RecipeUnlockingRequirement.deserialize(stream),
                recipeNetworkID = ProtoVAR.UInt.deserialize(stream),
            )
        }
    }
}
