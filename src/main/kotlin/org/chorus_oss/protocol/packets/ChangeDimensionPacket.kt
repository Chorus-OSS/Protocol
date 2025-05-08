package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.Vector3f

data class ChangeDimensionPacket(
    val dimension: Int,
    val position: Vector3f,
    val respawn: Boolean,
    val loadingScreenID: Int? = null,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.dimension)
        byteBuf.writeVector3f(this.position)
        byteBuf.writeBoolean(this.respawn)
        byteBuf.writeNotNull(this.loadingScreenID) { byteBuf.writeIntLE(it) }
    }

    override fun pid(): Int {
        return ProtocolInfo.CHANGE_DIMENSION_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
