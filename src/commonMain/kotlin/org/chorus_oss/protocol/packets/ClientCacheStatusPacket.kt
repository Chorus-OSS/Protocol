package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean

data class ClientCacheStatusPacket(
    val isCacheSupported: Boolean
) : Packet(id) {
    companion object : PacketCodec<ClientCacheStatusPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.CLIENT_CACHE_STATUS_PACKET

        override fun deserialize(stream: Source): ClientCacheStatusPacket {
            return ClientCacheStatusPacket(
                isCacheSupported = Proto.Boolean.deserialize(stream)
            )
        }

        override fun serialize(value: ClientCacheStatusPacket, stream: Sink) {
            Proto.Boolean.serialize(value.isCacheSupported, stream)
        }
    }
}
