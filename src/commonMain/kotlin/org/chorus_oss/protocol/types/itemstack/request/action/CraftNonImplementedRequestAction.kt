package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec

class CraftNonImplementedRequestAction : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CraftNonImplementedDeprecated

    companion object : ProtoCodec<CraftNonImplementedRequestAction> {
        override fun serialize(
            value: CraftNonImplementedRequestAction,
            stream: Sink
        ) = Unit

        override fun deserialize(stream: Source): CraftNonImplementedRequestAction = CraftNonImplementedRequestAction()
    }
}
