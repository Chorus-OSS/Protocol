package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.types.item.ItemInstance

data class CraftResultsDeprecatedRequestAction(
    val resultItems: List<ItemInstance>,
    val timesCrafted: Byte,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CRAFT_RESULTS_DEPRECATED

    companion object : ProtoCodec<CraftResultsDeprecatedRequestAction> {
        override fun serialize(
            value: CraftResultsDeprecatedRequestAction,
            stream: Sink
        ) {
            ProtoHelper.serializeList(value.resultItems, stream, ItemInstance)
            Proto.Byte.serialize(value.timesCrafted, stream)
        }

        override fun deserialize(stream: Source): CraftResultsDeprecatedRequestAction {
            return CraftResultsDeprecatedRequestAction(
                resultItems = ProtoHelper.deserializeList(stream, ItemInstance),
                timesCrafted = Proto.Byte.deserialize(stream),
            )
        }
    }
}
