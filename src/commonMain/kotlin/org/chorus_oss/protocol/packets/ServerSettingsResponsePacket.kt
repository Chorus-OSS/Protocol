package org.chorus_oss.protocol.packets

import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.ProtoVAR
import org.chorus_oss.protocol.core.types.Int
import org.chorus_oss.protocol.core.types.String


data class ServerSettingsResponsePacket(
    val formID: Int,
    val formData: String,
) : Packet(id) {
    companion object : PacketCodec<ServerSettingsResponsePacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.SERVER_SETTINGS_RESPONSE_PACKET

        override fun serialize(
            value: ServerSettingsResponsePacket,
            stream: Sink
        ) {
            ProtoVAR.Int.serialize(value.formID, stream)
            Proto.String.serialize(value.formData, stream)
        }

        override fun deserialize(stream: Source): ServerSettingsResponsePacket {
            return ServerSettingsResponsePacket(
                formID = ProtoVAR.Int.deserialize(stream),
                formData = Proto.String.deserialize(stream),
            )
        }
    }
}
