package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry

class RefreshEntitlementsPacket : Packet(id) {
    companion object : PacketCodec<RefreshEntitlementsPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.REFRESH_ENTITLEMENTS_PACKET

        override fun serialize(
            value: RefreshEntitlementsPacket,
            stream: Sink
        ) = Unit

        override fun deserialize(stream: Source): RefreshEntitlementsPacket = RefreshEntitlementsPacket()
    }
}