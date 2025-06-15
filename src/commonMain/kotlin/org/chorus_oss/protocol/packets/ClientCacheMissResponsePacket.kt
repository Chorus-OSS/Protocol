package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.ProtoHelper
import org.chorus_oss.protocol.types.CacheBlob

data class ClientCacheMissResponsePacket(
    val blobs: List<CacheBlob>
) : Packet(id) {
    companion object : PacketCodec<ClientCacheMissResponsePacket> {
        override val id: Int = 136

        override fun serialize(
            value: ClientCacheMissResponsePacket,
            stream: Sink
        ) {
            ProtoHelper.serializeList(value.blobs, stream, CacheBlob)
        }

        override fun deserialize(stream: Source): ClientCacheMissResponsePacket {
            return ClientCacheMissResponsePacket(
                blobs = ProtoHelper.deserializeList(stream, CacheBlob)
            )
        }
    }
}
