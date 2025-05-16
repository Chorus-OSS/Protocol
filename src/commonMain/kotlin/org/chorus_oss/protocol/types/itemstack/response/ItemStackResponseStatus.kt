package org.chorus_oss.protocol.types.itemstack.response

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.Byte

enum class ItemStackResponseStatus {
    OK,
    Error,
    InvalidRequestActionType,
    ActionRequestNotAllowed,
    ScreenHandlerEndRequestFailed,
    ItemRequestActionHandlerCommitFailed,
    InvalidRequestCraftActionType,
    InvalidCraftRequest,
    InvalidCraftRequestScreen,
    InvalidCraftResult,
    InvalidCraftResultIndex,
    InvalidCraftResultItem,
    InvalidItemNetId,
    MissingCreatedOutputContainer,
    FailedToSetCreatedItemOutputSlot,
    RequestAlreadyInProgress,
    FailedToInitSparseContainer,
    ResultTransferFailed,
    ExpectedItemSlotNotFullyConsumed,
    ExpectedAnywhereItemNotFullyConsumed,
    ItemAlreadyConsumedFromSlot,
    ConsumedTooMuchFromSlot,
    MismatchSlotExpectedConsumedItem,
    MismatchSlotExpectedConsumedItemNetIdVariant,
    FailedToMatchExpectedSlotConsumedItem,
    FailedToMatchExpectedAllowedAnywhereConsumedItem,
    ConsumedItemOutOfAllowedSlotRange,
    ConsumedItemNotAllowed,
    PlayerNotInCreativeMode,
    InvalidExperimentalRecipeRequest,
    FailedToCraftCreative,
    FailedToGetLevelRecipe,
    FailedToFindRecipeByNetId,
    MismatchedCraftingSize,
    MissingInputSparseContainer,
    MismatchedRecipeForInputGridItems,
    EmptyCraftResults,
    FailedToEnchant,
    MissingInputItem,
    InsufficientPlayerLevelToEnchant,
    MissingMaterialItem,
    MissingActor,
    UnknownPrimaryEffect,
    PrimaryEffectOutOfRange,
    PrimaryEffectUnavailable,
    SecondaryEffectOutOfRange,
    SecondaryEffectUnavailable,
    DstContainerEqualToCreatedOutputContainer,
    DstContainerAndSlotEqualToSrcContainerAndSlot,
    FailedToValidateSrcSlot,
    FailedToValidateDstSlot,
    InvalidAdjustedAmount,
    InvalidItemSetType,
    InvalidTransferAmount,
    CannotSwapItem,
    CannotPlaceItem,
    UnhandledItemSetType,
    InvalidRemovedAmount,
    InvalidRegion,
    CannotDropItem,
    CannotDestroyItem,
    InvalidSourceContainer,
    ItemNotConsumed,
    InvalidNumCrafts,
    InvalidCraftResultStackSize,
    CannotRemoveItem,
    CannotConsumeItem,
    ScreenStackError;

    companion object : ProtoCodec<ItemStackResponseStatus> {
        override fun serialize(
            value: ItemStackResponseStatus,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.ordinal.toByte(), stream)
        }

        override fun deserialize(stream: Source): ItemStackResponseStatus {
            return entries[Proto.Byte.deserialize(stream).toInt()]
        }
    }
}
