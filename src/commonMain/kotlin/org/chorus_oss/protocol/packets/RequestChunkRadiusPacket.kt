package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int


data class RequestChunkRadiusPacket(
    val chunkRadius: Int,
    val maxChunkRadius: Int,
) : Packet(id) {
    companion object : PacketCodec<RequestChunkRadiusPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.REQUEST_CHUNK_RADIUS_PACKET

        override fun serialize(
            value: RequestChunkRadiusPacket,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.chunkRadius, stream)
            ProtoVAR.Int.serialize(value.maxChunkRadius, stream)
        }

        override fun deserialize(stream: Source): RequestChunkRadiusPacket {
            return RequestChunkRadiusPacket(
                chunkRadius = ProtoVAR.Int.deserialize(stream),
                maxChunkRadius = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
