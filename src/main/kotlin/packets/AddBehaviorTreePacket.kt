package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

data class AddBehaviorTreePacket(
    val behaviorTreeJSON: String
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(behaviorTreeJSON)
    }

    override fun pid(): Int {
        return ProtocolInfo.ADD_BEHAVIOR_TREE_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
