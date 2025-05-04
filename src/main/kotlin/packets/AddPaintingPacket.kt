package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.Vector3f

import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.ActorRuntimeID
import org.chorus_oss.protocol.types.ActorUniqueID

data class AddPaintingPacket(
    val targetActorID: ActorUniqueID,
    val targetRuntimeID: ActorRuntimeID,
    val position: Vector3f,
    val direction: Int,
    val motif: String,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorUniqueID(this.targetActorID)
        byteBuf.writeActorRuntimeID(this.targetRuntimeID)
        byteBuf.writeVector3f(this.position)
        byteBuf.writeVarInt(this.direction)
        byteBuf.writeString(this.motif)
    }

    override fun pid(): Int {
        return ProtocolInfo.ADD_PAINTING_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
