package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

abstract class ItemStackRequestAction {
    abstract val type: Type

    companion object : ProtoCodec<ItemStackRequestAction> {
        enum class Type {
            TAKE,
            PLACE,
            SWAP,
            DROP,
            DESTROY,
            CONSUME,
            CREATE,

            @Deprecated("since v712")
            PLACE_IN_ITEM_CONTAINER,

            @Deprecated("since v712")
            TAKE_FROM_ITEM_CONTAINER,
            LAB_TABLE_COMBINE,
            BEACON_PAYMENT,
            MINE_BLOCK,
            CRAFT_RECIPE,
            CRAFT_RECIPE_AUTO,
            CRAFT_CREATIVE,
            CRAFT_RECIPE_OPTIONAL,
            CRAFT_REPAIR_AND_DISENCHANT,
            CRAFT_LOOM,
            CRAFT_NON_IMPLEMENTED_DEPRECATED,
            CRAFT_RESULTS_DEPRECATED;

            companion object : ProtoCodec<Type> {
                override fun serialize(
                    value: Type,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): Type {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override fun serialize(
            value: ItemStackRequestAction,
            stream: Sink
        ) {
            Type.serialize(value.type, stream)
            when (value) {
                is TakeRequestAction -> TakeRequestAction.serialize(value, stream)
                is PlaceRequestAction -> PlaceRequestAction.serialize(value, stream)
                is SwapRequestAction -> SwapRequestAction.serialize(value, stream)
                is DropRequestAction -> DropRequestAction.serialize(value, stream)
                is CreateRequestAction -> CreateRequestAction.serialize(value, stream)
                is PlaceInItemContainerRequestAction -> PlaceInItemContainerRequestAction.serialize(value, stream)
                is TakeFromItemContainerRequestAction -> TakeFromItemContainerRequestAction.serialize(value, stream)
                is LabTableCombineRequestAction -> LabTableCombineRequestAction.serialize(value, stream)
                is BeaconPaymentRequestAction -> BeaconPaymentRequestAction.serialize(value, stream)
                is MineBlockRequestAction -> MineBlockRequestAction.serialize(value, stream)
                is CraftRecipeRequestAction -> CraftRecipeRequestAction.serialize(value, stream)
                is AutoCraftRecipeRequestAction -> AutoCraftRecipeRequestAction.serialize(value, stream)
                is CraftCreativeRequestAction -> CraftCreativeRequestAction.serialize(value, stream)
                is CraftRecipeOptionalRequestAction -> CraftRecipeOptionalRequestAction.serialize(value, stream)
                is CraftGrindstoneRequestAction -> CraftGrindstoneRequestAction.serialize(value, stream)
                is CraftLoomRequestAction -> CraftLoomRequestAction.serialize(value, stream)
                is CraftNonImplementedRequestAction -> CraftNonImplementedRequestAction.serialize(value, stream)
                is CraftResultsDeprecatedRequestAction -> CraftResultsDeprecatedRequestAction.serialize(value, stream)
            }
        }

        override fun deserialize(stream: Source): ItemStackRequestAction {
            val type = Type.deserialize(stream)
            return when (type) {
                Type.TAKE -> TakeRequestAction.deserialize(stream)
                Type.PLACE -> PlaceRequestAction.deserialize(stream)
                Type.SWAP -> SwapRequestAction.deserialize(stream)
                Type.DROP -> DropRequestAction.deserialize(stream)
                Type.DESTROY -> DestroyRequestAction.deserialize(stream)
                Type.CONSUME -> ConsumeRequestAction.deserialize(stream)
                Type.CREATE -> CreateRequestAction.deserialize(stream)
                @Suppress("DEPRECATION")
                Type.PLACE_IN_ITEM_CONTAINER -> PlaceInItemContainerRequestAction.deserialize(stream)
                @Suppress("DEPRECATION")
                Type.TAKE_FROM_ITEM_CONTAINER -> TakeFromItemContainerRequestAction.deserialize(stream)
                Type.LAB_TABLE_COMBINE -> LabTableCombineRequestAction.deserialize(stream)
                Type.BEACON_PAYMENT -> BeaconPaymentRequestAction.deserialize(stream)
                Type.MINE_BLOCK -> MineBlockRequestAction.deserialize(stream)
                Type.CRAFT_RECIPE -> CraftRecipeRequestAction.deserialize(stream)
                Type.CRAFT_RECIPE_AUTO -> AutoCraftRecipeRequestAction.deserialize(stream)
                Type.CRAFT_CREATIVE -> CraftCreativeRequestAction.deserialize(stream)
                Type.CRAFT_RECIPE_OPTIONAL -> CraftRecipeOptionalRequestAction.deserialize(stream)
                Type.CRAFT_REPAIR_AND_DISENCHANT -> CraftGrindstoneRequestAction.deserialize(stream)
                Type.CRAFT_LOOM -> CraftLoomRequestAction.deserialize(stream)
                Type.CRAFT_NON_IMPLEMENTED_DEPRECATED -> CraftNonImplementedRequestAction.deserialize(stream)
                Type.CRAFT_RESULTS_DEPRECATED -> CraftResultsDeprecatedRequestAction.deserialize(stream)
            }
        }
    }
}
