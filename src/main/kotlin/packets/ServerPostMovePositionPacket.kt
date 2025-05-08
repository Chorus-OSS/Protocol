package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.Vector3f


class ServerPostMovePositionPacket : DataPacket() {
    lateinit var position: Vector3f

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVector3f(position)
    }

    override fun pid(): Int {
        return ProtocolInfo.SERVER_POST_MOVE_POSITION
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
