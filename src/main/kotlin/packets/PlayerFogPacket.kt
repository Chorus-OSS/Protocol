package org.chorus_oss.protocol.packets


import org.chorus_oss.chorus.utils.Identifier


class PlayerFogPacket : DataPacket() {
    //Fog stack containing fog effects from the /fog command
    @JvmField
    var fogStack: List<Fog> = ArrayList()

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeArray(
            fogStack
        ) { fog: Fog -> byteBuf.writeString(fog.identifier.toString()) }
    }

    /**
     * @param identifier The namespace id of this fog
     * @param userProvidedId User-specified feature id
     */
    @JvmRecord
    data class Fog(
        val identifier: Identifier,
        val userProvidedId: String
    )

    override fun pid(): Int {
        return ProtocolInfo.PLAYER_FOG_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
