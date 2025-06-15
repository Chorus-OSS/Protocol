package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.itemstack.response.ItemStackResponse


data class ItemStackResponsePacket(
    val responses: List<ItemStackResponse>
) : Packet(id) {
    companion object : PacketCodec<ItemStackResponsePacket> {
        override val id: Int = 148

        override fun serialize(
            value: ItemStackResponsePacket,
            stream: Sink
        ) {
            ProtoHelper.serializeList(value.responses, stream, ItemStackResponse)
        }

        override fun deserialize(stream: Source): ItemStackResponsePacket {
            return ItemStackResponsePacket(
                responses = ProtoHelper.deserializeList(stream, ItemStackResponse)
            )
        }
    }
}
