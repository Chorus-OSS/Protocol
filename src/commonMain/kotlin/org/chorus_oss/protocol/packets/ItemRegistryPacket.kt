package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.item.ItemEntry

data class ItemRegistryPacket(
    val items: List<ItemEntry>
) : Packet(id) {
    companion object : PacketCodec<ItemRegistryPacket> {
        override val id: Int = 162

        override fun serialize(value: ItemRegistryPacket, stream: Sink) {
            ProtoHelper.serializeList(value.items, stream, ItemEntry)
        }

        override fun deserialize(stream: Source): ItemRegistryPacket {
            return ItemRegistryPacket(
                items = ProtoHelper.deserializeList(stream, ItemEntry)
            )
        }
    }
}
