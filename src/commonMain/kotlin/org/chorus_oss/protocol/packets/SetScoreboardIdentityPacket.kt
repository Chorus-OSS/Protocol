package org.chorus_oss.protocol.packets


import java.util.*


class SetScoreboardIdentityPacket : Packet(id) {
    val entries: List<Entry> = listOf()
    var action: Action? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(action!!.ordinal.toByte().toInt())

        for (entry in this.entries) {
            byteBuf.writeVarLong(entry.scoreboardId)
            byteBuf.writeUUID(entry.uuid!!)
        }
    }

    enum class Action {
        ADD,
        REMOVE
    }

    class Entry {
        var scoreboardId: Long = 0
        var uuid: UUID? = null
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_SCOREBOARD_IDENTITY_PACKET
    }


}
