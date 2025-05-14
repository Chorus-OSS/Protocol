package org.chorus_oss.protocol.packets

import org.chorus_oss.chorus.math.BlockVector3

import org.chorus_oss.protocol.types.LabTableReactionType
import org.chorus_oss.protocol.types.LabTableType


class LabTablePacket : Packet(id) {
    var actionType: LabTableType? = null
    var blockPosition: BlockVector3? = null
    var reactionType: LabTableReactionType? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeByte(actionType!!.ordinal.toByte().toInt())
        byteBuf.writeBlockVector3(blockPosition!!)
        byteBuf.writeByte(reactionType!!.ordinal.toByte().toInt())
    }

    override fun pid(): Int {
        return ProtocolInfo.LAB_TABLE_PACKET
    }


}
