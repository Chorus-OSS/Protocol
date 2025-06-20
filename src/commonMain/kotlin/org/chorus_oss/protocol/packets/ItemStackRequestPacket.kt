package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.itemstack.request.ItemStackRequest


data class ItemStackRequestPacket(
    val requests: List<ItemStackRequest>
) : Packet(id) {
    companion object : PacketCodec<ItemStackRequestPacket> {
        override val id: Int = 147

        override fun serialize(value: ItemStackRequestPacket, stream: Sink) {
            ProtoHelper.serializeList(value.requests, stream, ItemStackRequest)
        }

        override fun deserialize(stream: Source): ItemStackRequestPacket {
            return ItemStackRequestPacket(
                requests = ProtoHelper.deserializeList(stream, ItemStackRequest)
            )
        }
    }
}
