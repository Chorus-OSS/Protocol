package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.core.PacketRegistry
import org.chorus_oss.protocol.types.ActorUniqueID

data class CameraPacket(
    val cameraID: ActorUniqueID,
    val targetPlayerID: ActorUniqueID,
) : Packet(id) {
    companion object : PacketCodec<CameraPacket> {
        init { PacketRegistry.register(this) }

        override val id: Int
            get() = ProtocolInfo.CAMERA_PACKET

        override fun deserialize(stream: Source): CameraPacket {
            return CameraPacket(
                cameraID = ActorUniqueID.deserialize(stream),
                targetPlayerID = ActorUniqueID.deserialize(stream),
            )
        }

        override fun serialize(value: CameraPacket, stream: Sink) {
            ActorUniqueID.serialize(value.cameraID, stream)
            ActorUniqueID.serialize(value.targetPlayerID, stream)
        }
    }
}
