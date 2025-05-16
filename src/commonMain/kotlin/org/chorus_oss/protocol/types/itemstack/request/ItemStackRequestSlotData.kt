package org.chorus_oss.protocol.types.itemstack.request

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.types.inventory.FullContainerName

data class ItemStackRequestSlotData(
    val container: FullContainerName,
    val slot: Byte,
    val stackNetworkID: Int,
) {
    companion object : ProtoCodec<ItemStackRequestSlotData> {
        override fun serialize(
            value: ItemStackRequestSlotData,
            stream: Sink
        ) {
            FullContainerName.serialize(value.container, stream)
            Proto.Byte.serialize(value.slot, stream)
            ProtoVAR.Int.serialize(value.stackNetworkID, stream)
        }

        override fun deserialize(stream: Source): ItemStackRequestSlotData {
            return ItemStackRequestSlotData(
                container = FullContainerName.deserialize(stream),
                slot = Proto.Byte.deserialize(stream),
                stackNetworkID = ProtoVAR.Int.deserialize(stream)
            )
        }
    }
}
