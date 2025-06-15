package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

abstract class ItemStackRequestAction {
    abstract val type: Type

    @Suppress("DEPRECATION")
    companion object : ProtoCodec<ItemStackRequestAction> {
        enum class Type {
            Take,
            Place,
            Swap,
            Drop,
            Destroy,
            Consume,
            Create,

            @Deprecated("since v712")
            PlaceInItemContainer,

            @Deprecated("since v712")
            TakeFromItemContainer,
            LabTableCombine,
            BeaconPayment,
            MineBlock,
            CraftRecipe,
            CraftRecipeAuto,
            CraftCreative,
            CraftRecipeOptional,
            CraftRepairAndDisenchant,
            CraftLoom,
            CraftNonImplementedDeprecated,
            CraftResultsDeprecated;

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
                Type.Take -> TakeRequestAction.deserialize(stream)
                Type.Place -> PlaceRequestAction.deserialize(stream)
                Type.Swap -> SwapRequestAction.deserialize(stream)
                Type.Drop -> DropRequestAction.deserialize(stream)
                Type.Destroy -> DestroyRequestAction.deserialize(stream)
                Type.Consume -> ConsumeRequestAction.deserialize(stream)
                Type.Create -> CreateRequestAction.deserialize(stream)
                Type.PlaceInItemContainer -> PlaceInItemContainerRequestAction.deserialize(stream)
                Type.TakeFromItemContainer -> TakeFromItemContainerRequestAction.deserialize(stream)
                Type.LabTableCombine -> LabTableCombineRequestAction.deserialize(stream)
                Type.BeaconPayment -> BeaconPaymentRequestAction.deserialize(stream)
                Type.MineBlock -> MineBlockRequestAction.deserialize(stream)
                Type.CraftRecipe -> CraftRecipeRequestAction.deserialize(stream)
                Type.CraftRecipeAuto -> AutoCraftRecipeRequestAction.deserialize(stream)
                Type.CraftCreative -> CraftCreativeRequestAction.deserialize(stream)
                Type.CraftRecipeOptional -> CraftRecipeOptionalRequestAction.deserialize(stream)
                Type.CraftRepairAndDisenchant -> CraftGrindstoneRequestAction.deserialize(stream)
                Type.CraftLoom -> CraftLoomRequestAction.deserialize(stream)
                Type.CraftNonImplementedDeprecated -> CraftNonImplementedRequestAction.deserialize(stream)
                Type.CraftResultsDeprecated -> CraftResultsDeprecatedRequestAction.deserialize(stream)
            }
        }
    }
}
