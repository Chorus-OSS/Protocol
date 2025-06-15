package org.chorus_oss.protocol.packets


import kotlinx.io.Sink
import kotlinx.io.Source
import org.chorus_oss.protocol.core.Packet
import org.chorus_oss.protocol.core.PacketCodec
import org.chorus_oss.protocol.types.ActorUniqueID

data class CameraPacket(
    val cameraID: ActorUniqueID,
    val targetPlayerID: ActorUniqueID,
) : Packet(id) {
    companion object : PacketCodec<CameraPacket> {
        override val id: Int = 73

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
