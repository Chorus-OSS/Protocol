package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.DisconnectFailReason

data class DisconnectPacket(
    val reason: DisconnectFailReason,
    val hideDisconnectionScreen: Boolean,
    val message: String,
    val filteredMessage: String,
) : Packet(id) {
    companion object : PacketCodec<DisconnectPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.DISCONNECT_PACKET

        override fun serialize(value: DisconnectPacket, stream: Sink) {
            DisconnectFailReason.serialize(value.reason, stream)
            Proto.Boolean.serialize(value.hideDisconnectionScreen, stream)
            if (!value.hideDisconnectionScreen) {
                Proto.String.serialize(value.message, stream)
                Proto.String.serialize(value.filteredMessage, stream)
            }
        }

        override fun deserialize(stream: Source): DisconnectPacket {
            val hideDisconnectionScreen: Boolean
            return DisconnectPacket(
                reason = DisconnectFailReason.deserialize(stream),
                hideDisconnectionScreen = Proto.Boolean.deserialize(stream).also { hideDisconnectionScreen = it },
                message = if (!hideDisconnectionScreen) Proto.String.deserialize(stream) else "",
                filteredMessage = if (!hideDisconnectionScreen) Proto.String.deserialize(stream) else "",
            )
        }
    }
}
