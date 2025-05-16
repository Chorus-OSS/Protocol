package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.ProtoCodec

class LabTableCombineRequestAction : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.LAB_TABLE_COMBINE

    companion object : ProtoCodec<LabTableCombineRequestAction> {
        override fun serialize(
            value: LabTableCombineRequestAction,
            stream: Sink
        ) = Unit

        override fun deserialize(stream: Source): LabTableCombineRequestAction = LabTableCombineRequestAction()
    }
}
