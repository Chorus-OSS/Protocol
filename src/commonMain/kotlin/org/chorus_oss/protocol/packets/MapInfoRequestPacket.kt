package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.ProtoCodec
import org.chorus_oss.protocol.core.ProtoLE
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.UShort
import org.chorus_oss.protocol.types.ActorUniqueID
import org.chorus_oss.protocol.types.Color
import org.chorus_oss.protocol.types.IColorRGBA


data class MapInfoRequestPacket(
    val mapID: ActorUniqueID,
    val clientPixels: List<PixelRequest>,
) : Packet(id) {
    companion object : PacketCodec<MapInfoRequestPacket> {
        init { PacketRegistry.register(this) }

        data class PixelRequest(
            val color: Color,
            val index: UShort,
        ) {
            companion object : ProtoCodec<PixelRequest> {
                override fun serialize(
                    value: PixelRequest,
                    stream: Sink
                ) {
                    IColorRGBA.serialize(value.color, stream)
                    ProtoLE.UShort.serialize(value.index, stream)
                }

                override fun deserialize(stream: Source): PixelRequest {
                    return PixelRequest(
                        color = IColorRGBA.deserialize(stream),
                        index = ProtoLE.UShort.deserialize(stream),
                    )
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.MAP_INFO_REQUEST_PACKET

        override fun serialize(value: MapInfoRequestPacket, stream: Sink) {
            ActorUniqueID.serialize(value.mapID, stream)
            ProtoLE.Int.serialize(value.clientPixels.size, stream)
            value.clientPixels.forEach { PixelRequest.serialize(it, stream) }
        }

        override fun deserialize(stream: Source): MapInfoRequestPacket {
            return MapInfoRequestPacket(
                mapID = ActorUniqueID.deserialize(stream),
                clientPixels = List(ProtoLE.Int.deserialize(stream)) {
                    PixelRequest.deserialize(stream)
                }
            )
        }
    }
}
