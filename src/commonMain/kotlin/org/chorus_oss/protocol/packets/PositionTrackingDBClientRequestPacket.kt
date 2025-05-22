package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.*
import org.chorus_oss.protocol.core.types.Byte
import org.chorus_oss.protocol.core.types.Int


data class PositionTrackingDBClientRequestPacket(
    val requestAction: RequestAction,
    val trackingID: Int,
) : Packet(id) {
    companion object : PacketCodec<PositionTrackingDBClientRequestPacket> {
        init {
            PacketRegistry.register(this)
        }

        enum class RequestAction {
            Query;

            companion object : ProtoCodec<RequestAction> {
                override fun serialize(
                    value: RequestAction,
                    stream: Sink
                ) {
                    Proto.Byte.serialize(value.ordinal.toByte(), stream)
                }

                override fun deserialize(stream: Source): RequestAction {
                    return entries[Proto.Byte.deserialize(stream).toInt()]
                }
            }
        }

        override val id: Int
            get() = ProtocolInfo.POSITION_TRACKING_DB_CLIENT_REQUEST_PACKET

        override fun serialize(
            value: PositionTrackingDBClientRequestPacket,
            stream: Sink
        ) {
            RequestAction.serialize(value.requestAction, stream)
            ProtoVAR.Int.serialize(value.trackingID, stream)
        }

        override fun deserialize(stream: Source): PositionTrackingDBClientRequestPacket {
            return PositionTrackingDBClientRequestPacket(
                requestAction = RequestAction.deserialize(stream),
                trackingID = ProtoVAR.Int.deserialize(stream),
            )
        }
    }
}
