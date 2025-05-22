package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.core.Proto
import org.chorus_oss.protocol.core.types.Boolean
import org.chorus_oss.protocol.core.types.String
import org.chorus_oss.protocol.types.camera.aimassist.ClientCameraAimAssistPacketAction

data class ClientCameraAimAssistPacket(
    val cameraPresetID: String,
    val action: ClientCameraAimAssistPacketAction,
    val allowAimAssist: Boolean,
) : Packet(id) {
    companion object : PacketCodec<ClientCameraAimAssistPacket> {
        init {
            PacketRegistry.register(this)
        }

        override val id: Int
            get() = ProtocolInfo.CLIENT_CAMERA_AIM_ASSIST_PACKET

        override fun deserialize(stream: Source): ClientCameraAimAssistPacket {
            return ClientCameraAimAssistPacket(
                cameraPresetID = Proto.String.deserialize(stream),
                action = ClientCameraAimAssistPacketAction.deserialize(stream),
                allowAimAssist = Proto.Boolean.deserialize(stream),
            )
        }

        override fun serialize(value: ClientCameraAimAssistPacket, stream: Sink) {
            Proto.String.serialize(value.cameraPresetID, stream)
            ClientCameraAimAssistPacketAction.serialize(value.action, stream)
            Proto.Boolean.serialize(value.allowAimAssist, stream)
        }
    }
}
