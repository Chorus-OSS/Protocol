package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.hud.HudElement
import org.chorus_oss.protocol.types.hud.HudVisibility

data class SetHudPacket(
    val elements: List<HudElement>,
    val visibility: HudVisibility,
) : Packet(id) {
    companion object : PacketCodec<SetHudPacket> {
        override val id: Int = 308

        override fun serialize(value: SetHudPacket, stream: Sink) {
            ProtoHelper.serializeList(value.elements, stream, HudElement)
            HudVisibility.serialize(value.visibility, stream)
        }

        override fun deserialize(stream: Source): SetHudPacket {
            return SetHudPacket(
                elements = ProtoHelper.deserializeList(stream, HudElement),
                visibility = HudVisibility.deserialize(stream),
            )
        }
    }
}
