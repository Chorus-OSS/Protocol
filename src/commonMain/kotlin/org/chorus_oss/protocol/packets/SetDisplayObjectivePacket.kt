package org.chorus_oss.protocol.packets


import org.chorus_oss.chorus.scoreboard.data.DisplaySlot
import org.chorus_oss.chorus.scoreboard.data.SortOrder


class SetDisplayObjectivePacket : Packet(id) {
    @JvmField
    var displaySlot: DisplaySlot? = null

    @JvmField
    var objectiveName: String? = null

    @JvmField
    var displayName: String? = null

    @JvmField
    var criteriaName: String? = null

    @JvmField
    var sortOrder: SortOrder? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeString(displaySlot!!.slotName)
        byteBuf.writeString(objectiveName!!)
        byteBuf.writeString(displayName!!)
        byteBuf.writeString(criteriaName!!)
        byteBuf.writeVarInt(sortOrder!!.ordinal)
    }

    override fun pid(): Int {
        return ProtocolInfo.SET_DISPLAY_OBJECTIVE_PACKET
    }


}
