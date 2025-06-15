package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean

data class ClientCacheStatusPacket(
    val isCacheSupported: Boolean
) : Packet(id) {
    companion object : PacketCodec<ClientCacheStatusPacket> {
        override val id: Int = 129

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
