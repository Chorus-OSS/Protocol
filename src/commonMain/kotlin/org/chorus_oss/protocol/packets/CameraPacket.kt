package org.chorus_oss.protocol.packets


import kotlinx.io.Buffer
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorUniqueID

data class CameraPacket(
    val cameraID: ActorUniqueID,
    val targetPlayerID: ActorUniqueID,
) {
    companion object : PacketCodec<CameraPacket> {
        override val id: Int
            get() = ProtocolInfo.CAMERA_PACKET

        override fun deserialize(stream: Buffer): CameraPacket {
            return CameraPacket(
                cameraID = ActorUniqueID.deserialize(stream),
                targetPlayerID = ActorUniqueID.deserialize(stream),
            )
        }

        override fun serialize(value: CameraPacket, stream: Buffer) {
            ActorUniqueID.serialize(value.cameraID, stream)
            ActorUniqueID.serialize(value.targetPlayerID, stream)
        }
    }
}
