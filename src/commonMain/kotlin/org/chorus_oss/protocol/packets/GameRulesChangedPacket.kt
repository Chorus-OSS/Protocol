package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.level.GameRules


class GameRulesChangedPacket : DataPacket() {
    @JvmField
    var gameRules: GameRules? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeGameRules(gameRules!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.GAME_RULES_CHANGED_PACKET
    }

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
