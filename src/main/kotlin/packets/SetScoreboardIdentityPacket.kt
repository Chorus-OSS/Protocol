package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.ProtocolInfo
import java.util.*


class SetScoreboardIdentityPacket : DataPacket() {
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

    override fun handle(handler: PacketHandler) {
        handler.handle(this)
    }
}
