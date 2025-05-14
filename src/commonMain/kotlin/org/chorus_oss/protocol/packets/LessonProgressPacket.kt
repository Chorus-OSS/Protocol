package org.chorus_oss.protocol.packets


import org.chorus_oss.protocol.types.LessonAction


class LessonProgressPacket : Packet(id) {
    var action: LessonAction? = null
    var score: Int = 0
    var activityId: String? = null

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeVarInt(action!!.ordinal)
        byteBuf.writeVarInt(score)
        byteBuf.writeString(activityId!!)
    }

    override fun pid(): Int {
        return ProtocolInfo.LESSON_PROGRESS_PACKET
    }



    companion object : PacketCodec<LessonProgressPacket> {
        override fun deserialize(stream: Source): LessonProgressPacket {
            val packet = LessonProgressPacket()

            packet.action = LessonAction.entries[byteBuf.readVarInt()]
            packet.score = byteBuf.readVarInt()
            packet.activityId = Proto.String.deserialize(stream)

            return packet
        }
    }
}
