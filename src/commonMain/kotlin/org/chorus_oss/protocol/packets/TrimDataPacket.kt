package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.TrimMaterial
import org.chorus_oss.protocol.types.TrimPattern

data class TrimDataPacket(
    val patterns: List<TrimPattern>,
    val materials: List<TrimMaterial>,
) : Packet(id) {
    companion object : PacketCodec<TrimDataPacket> {
        override val id: Int = 302

        override fun serialize(value: TrimDataPacket, stream: Sink) {
            ProtoHelper.serializeList(value.patterns, stream, TrimPattern)
            ProtoHelper.serializeList(value.materials, stream, TrimMaterial)
        }

        override fun deserialize(stream: Source): TrimDataPacket {
            return TrimDataPacket(
                patterns = ProtoHelper.deserializeList(stream, TrimPattern),
                materials = ProtoHelper.deserializeList(stream, TrimMaterial),
            )
        }
    }
}
