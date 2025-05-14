package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.DisconnectFailReason

class DisconnectPacket : Packet(id) {
    var reason: DisconnectFailReason = DisconnectFailReason.UNKNOWN

    @JvmField
    var hideDisconnectionScreen: Boolean = false

    @JvmField
    var message: String = ""
    private var filteredMessage = ""

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(reason.ordinal)
        byteBuf.writeBoolean(this.hideDisconnectionScreen)
        if (!this.hideDisconnectionScreen) {
            byteBuf.writeString(message)
            byteBuf.writeString(this.filteredMessage)
        }
    }

    override fun pid(): Int {
        return ProtocolInfo.DISCONNECT_PACKET
    }


}
