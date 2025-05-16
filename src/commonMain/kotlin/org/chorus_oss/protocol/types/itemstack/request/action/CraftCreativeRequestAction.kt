package org.chorus_oss.protocol.types.itemstack.request.action

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.UInt

data class CraftCreativeRequestAction(
    val creativeItemNetworkId: UInt,
    val numberOfCrafts: Byte,
) : ItemStackRequestAction() {
    override val type: ItemStackRequestAction.Companion.Type
        get() = ItemStackRequestAction.Companion.Type.CRAFT_CREATIVE

    companion object : ProtoCodec<CraftCreativeRequestAction> {
        override fun serialize(
            value: CraftCreativeRequestAction,
            stream: Sink
        ) {
            ProtoVAR.UInt.serialize(value.creativeItemNetworkId, stream)
            Proto.Byte.serialize(value.numberOfCrafts, stream)
        }

        override fun deserialize(stream: Source): CraftCreativeRequestAction {
            return CraftCreativeRequestAction(
                creativeItemNetworkId = ProtoVAR.UInt.deserialize(stream),
                numberOfCrafts = Proto.Byte.deserialize(stream),
            )
        }
    }
}
