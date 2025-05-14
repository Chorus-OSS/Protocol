package org.chorus_oss.protocol.packets


class ServerSettingsResponsePacket : Packet(id) {
    @JvmField
    var formId: Int = 0

    @JvmField
    var data: String? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(this.formId)
        byteBuf.writeString(data!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.SERVER_SETTINGS_RESPONSE_PACKET
    }


}
