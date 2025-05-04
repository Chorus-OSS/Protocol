package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.ActorUniqueID

data class CameraPacket(
    val cameraID: ActorUniqueID,
    val targetPlayerID: ActorUniqueID,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.cameraID)
        byteBuf.writeActorUniqueID(this.targetPlayerID)
    }

    override fun pid(): Int {
        return ProtocolInfo.CAMERA_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
