package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.item.desciptor.ItemDescriptorCount

data class RecipeUnlockingRequirement(
    val context: UnlockingContext,
    val ingredients: List<ItemDescriptorCount>?,
) {
    companion object : ProtoCodec<RecipeUnlockingRequirement> {
        enum class UnlockingContext {
            None,
            AlwaysUnlocked,
            PlayerInWater,
            PlayerHasManyItems;

            companion object : ProtoCodec<UnlockingContext> {
                override fun serialize(
                    value: UnlockingContext,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): UnlockingContext {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(
            value: RecipeUnlockingRequirement,
            stream: Sink
        ) {
            UnlockingContext.serialize(value.context, stream)
            when (value.context) {
                UnlockingContext.None -> ProtoHelper.serializeList(value.ingredients as List<ItemDescriptorCount>, stream, ItemDescriptorCount)
                else -> Unit
            }
        }

        override fun deserialize(stream: Source): RecipeUnlockingRequirement {
            val context: UnlockingContext
            return RecipeUnlockingRequirement(
                context = UnlockingContext.deserialize(stream).also { context = it },
                ingredients = when (context) {
                    UnlockingContext.None -> ProtoHelper.deserializeList(stream, ItemDescriptorCount)
                    else -> null
                }
            )
        }
    }
}