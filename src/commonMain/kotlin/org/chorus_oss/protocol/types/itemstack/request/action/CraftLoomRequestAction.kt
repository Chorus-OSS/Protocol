package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.types.String

data class CraftLoomRequestAction(
    val patternID: String
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CraftLoom

    companion object : ProtoCodec<CraftLoomRequestAction> {
        override fun serialize(
            value: CraftLoomRequestAction,
            stream: Sink
        ) {
            Proto.String.serialize(value.patternID, stream)
        }

        override fun deserialize(stream: Source): CraftLoomRequestAction {
            return CraftLoomRequestAction(
                patternID = Proto.String.deserialize(stream)
            )
        }
    }
}