package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.level.GameRules


class GameRulesChangedPacket : Packet(id) {
    @JvmField
    var gameRules: GameRules? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeGameRules(gameRules!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.GAME_RULES_CHANGED_PACKET
    }


}
