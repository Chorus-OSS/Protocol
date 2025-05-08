package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.AgentActionType

data class AgentActionEventPacket(
    val requestId: String,
    val action: AgentActionType,
    val response: String,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(requestId)
        byteBuf.writeInt(action.ordinal)
        byteBuf.writeString(response)
    }

    override fun pid(): Int {
        return ProtocolInfo.AGENT_ACTION_EVENT_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
