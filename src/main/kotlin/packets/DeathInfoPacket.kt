package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.lang.TranslationContainer

import org.chorus_oss.protocol.ProtocolInfo


class DeathInfoPacket : DataPacket() {
    @JvmField
    var translation: TranslationContainer? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(translation!!.text)
        byteBuf.writeArray(
            translation!!.parameters
        ) { str: String? -> byteBuf.writeString(str!!) }
    }

    override fun pid(): Int {
        return ProtocolInfo.DEATH_INFO_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
