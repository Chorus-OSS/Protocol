package org.chorus_oss.protocol.types.itemstack.response

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String

data class ItemStackResponseSlot(
    val slot: Byte,
    val hotbarSlot: Byte,
    val count: Byte,
    val stackNetworkID: Int,
    val customName: String,
    val filteredCustomName: String,
    val durabilityCorrection: Int
) {
    companion object : ProtoCodec<ItemStackResponseSlot> {
        override fun serialize(
            value: ItemStackResponseSlot,
            stream: Sink
        ) {
            Proto.Byte.serialize(value.slot, stream)
            Proto.Byte.serialize(value.hotbarSlot, stream)
            Proto.Byte.serialize(value.count, stream)
            ProtoVAR.Int.serialize(value.stackNetworkID, stream)
            Proto.String.serialize(value.customName, stream)
            Proto.String.serialize(value.filteredCustomName, stream)
            ProtoVAR.Int.serialize(value.durabilityCorrection, stream)
        }

        override fun deserialize(stream: Source): ItemStackResponseSlot {
            return ItemStackResponseSlot(
                slot = Proto.Byte.deserialize(stream),
                hotbarSlot = Proto.Byte.deserialize(stream),
                count = Proto.Byte.deserialize(stream),
                stackNetworkID = ProtoVAR.Int.deserialize(stream),
                customName = Proto.String.deserialize(stream),
                filteredCustomName = Proto.String.deserialize(stream),
                durabilityCorrection = ProtoVAR.Int.deserialize(stream)
            )
        }
    }
}