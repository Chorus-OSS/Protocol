package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.String


data class ToastRequestPacket(
    val title: String,
    val message: String,
) : Packet(id) {
    companion object : PacketCodec<ToastRequestPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.TOAST_REQUEST_PACKET

        override fun serialize(value: ToastRequestPacket, stream: Sink) {
            Proto.String.serialize(value.title, stream)
            Proto.String.serialize(value.message, stream)
        }

        override fun deserialize(stream: Source): ToastRequestPacket {
            return ToastRequestPacket(
                title = Proto.String.deserialize(stream),
                message = Proto.String.deserialize(stream),
            )
        }
    }
}
