package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec

class RefreshEntitlementsPacket : Packet(id) {
    companion object : PacketCodec<RefreshEntitlementsPacket> {
        override val id: Int = 305

        override fun serialize(
            value: RefreshEntitlementsPacket,
            stream: Sink
        ) = Unit

        override fun deserialize(stream: Source): RefreshEntitlementsPacket = RefreshEntitlementsPacket()
    }
}