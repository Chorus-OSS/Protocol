package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo

data class CodeBuilderPacket(
    val url: String,
    val shouldOpenCodeBuilder: Boolean,
) : DataPacket(), PacketEncoder {
    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(url)
        byteBuf.writeBoolean(shouldOpenCodeBuilder)
    }

    override fun pid(): Int {
        return ProtocolInfo.CODE_BUILDER_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
