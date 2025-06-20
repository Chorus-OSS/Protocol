package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.creative.CreativeGroup
import org.chorus_oss.protocol.types.creative.CreativeItem

data class CreativeContentPacket(
    val groups: List<CreativeGroup>,
    val items: List<CreativeItem>,
) : Packet(id) {
    companion object : PacketCodec<CreativeContentPacket> {
        override val id: Int = 145

        override fun serialize(value: CreativeContentPacket, stream: Sink) {
            ProtoHelper.serializeList(value.groups, stream, CreativeGroup)
            ProtoHelper.serializeList(value.items, stream, CreativeItem)
        }

        override fun deserialize(stream: Source): CreativeContentPacket {
            return CreativeContentPacket(
                groups = ProtoHelper.deserializeList(stream, CreativeGroup),
                items = ProtoHelper.deserializeList(stream, CreativeItem),
            )
        }
    }
}
