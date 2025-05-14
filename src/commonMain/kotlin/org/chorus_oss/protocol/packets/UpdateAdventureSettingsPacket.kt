package org.chorus_oss.protocol.packets


class UpdateAdventureSettingsPacket : Packet(id) {
    @JvmField
    var noPvM: Boolean = false

    @JvmField
    var noMvP: Boolean = false

    @JvmField
    var immutableWorld: Boolean = false

    @JvmField
    var showNameTags: Boolean = false

    @JvmField
    var autoJump: Boolean = false

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeBoolean(noPvM)
        byteBuf.writeBoolean(noMvP)
        byteBuf.writeBoolean(immutableWorld)
        byteBuf.writeBoolean(showNameTags)
        byteBuf.writeBoolean(autoJump)
    }

    override fun pid(): Int {
        return ProtocolInfo.UPDATE_ADVENTURE_SETTINGS_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
