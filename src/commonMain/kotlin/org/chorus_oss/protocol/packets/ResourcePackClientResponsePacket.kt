package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.core.types.UShort


data class ResourcePackClientResponsePacket(
    val response: Response,
    val packsToDownload: List<String>
) : Packet(id) {
    companion object : PacketCodec<ResourcePackClientResponsePacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class Response(val net: Byte) {
            Refused(1),
            SendPacks(2),
            AllPacksDownloaded(3),
            Completed(4);

            companion object : ProtoCodec<Response> {
                override fun serialize(
                    value: Response,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.net, stream)
                }

                override fun deserialize(stream: Source): Response {
                    return Proto.Byte.deserialize(stream).let {
                        entries.find { e -> e.net == it }!!
                    }
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.RESOURCE_PACK_CLIENT_RESPONSE_PACKET

        override fun serialize(
            value: ResourcePackClientResponsePacket,
            stream: Sink
        ) {
            Response.serialize(value.response, stream)
            value.packsToDownload.let { packetsToDownload ->
                ProtoLE.UShort.serialize(packetsToDownload.size.toUShort(), stream)
                packetsToDownload.forEach { Proto.String.serialize(it, stream) }
            }
        }

        override fun deserialize(stream: Source): ResourcePackClientResponsePacket {
            return ResourcePackClientResponsePacket(
                response = Response.deserialize(stream),
                packsToDownload = List(ProtoLE.UShort.deserialize(stream).toInt()) {
                    Proto.String.deserialize(stream)
                },
            )
        }
    }
}
