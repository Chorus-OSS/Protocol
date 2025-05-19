package org.chorus_oss.protocol.types.recipe

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.UInt
import org.chorus_oss.protocol.core.types.Uuid
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class MultiRecipe(
    val uuid: Uuid,
    val recipeNetworkID: UInt,
) : Recipe() {
    override val type: Recipe.Companion.Type
        get() = Recipe.Companion.Type.Multi

    companion object : ProtoCodec<MultiRecipe> {
        override fun serialize(value: MultiRecipe, stream: Sink) {
            Proto.Uuid.serialize(value.uuid, stream)
            ProtoVAR.UInt.serialize(value.recipeNetworkID, stream)
        }

        override fun deserialize(stream: Source): MultiRecipe {
            return MultiRecipe(
                uuid = Proto.Uuid.deserialize(stream),
                recipeNetworkID = ProtoVAR.UInt.deserialize(stream),
            )
        }
    }
}
