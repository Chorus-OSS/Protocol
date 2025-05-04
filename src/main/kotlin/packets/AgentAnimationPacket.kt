package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import org.chorus_oss.protocol.types.ActorRuntimeID

data class AgentAnimationPacket(
    val animation: Byte,
    val runtimeID: ActorRuntimeID,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(animation.toInt())
        byteBuf.writeActorRuntimeID(this.runtimeID)
    }

    override fun pid(): Int {
        return ProtocolInfo.AGENT_ANIMATION
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
