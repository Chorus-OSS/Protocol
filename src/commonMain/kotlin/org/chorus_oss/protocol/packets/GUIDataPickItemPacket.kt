package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String


data class GUIDataPickItemPacket(
    val itemName: String,
    val itemEffects: String,
    val hotbarSlot: Int,
) : Packet(id) {
    companion object : PacketCodec<GUIDataPickItemPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.GUI_DATA_PICK_ITEM_PACKET

        override fun serialize(value: GUIDataPickItemPacket, stream: Sink) {
            Proto.String.serialize(value.itemName, stream)
            Proto.String.serialize(value.itemEffects, stream)
            ProtoLE.Int.serialize(value.hotbarSlot, stream)
        }

        override fun deserialize(stream: Source): GUIDataPickItemPacket {
            return GUIDataPickItemPacket(
                itemName = Proto.String.deserialize(stream),
                itemEffects = Proto.String.deserialize(stream),
                hotbarSlot = ProtoLE.Int.deserialize(stream)
            )
        }
    }
}
