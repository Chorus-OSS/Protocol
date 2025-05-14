package org.chorus_oss.protocol.packets


class NPCRequestPacket : Packet(id) {
    var entityRuntimeId: Long = 0
    var requestType: RequestType = RequestType.SET_SKIN
    var data: String = ""
    var skinType: Int = 0
    var sceneName: String = ""

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(this.entityRuntimeId)
        byteBuf.writeByte(requestType.ordinal.toByte().toInt())
        byteBuf.writeString(this.data)
        byteBuf.writeByte(skinType.toByte().toInt())
        byteBuf.writeString(this.sceneName)
    }

    enum class RequestType {
        SET_ACTIONS,
        EXECUTE_ACTION,
        EXECUTE_CLOSING_COMMANDS,
        SET_NAME,
        SET_SKIN,
        SET_INTERACTION_TEXT,
        EXECUTE_OPENING_COMMANDS
    }

    override fun pid(): Int {
        return ProtocolInfo.NPC_REQUEST_PACKET
    }



    companion object : PacketCodec<NPCRequestPacket> {
        override fun deserialize(stream: Source): NPCRequestPacket {
            val packet = NPCRequestPacket()

            packet.entityRuntimeId = byteBuf.readActorRuntimeID()
            packet.requestType = RequestType.entries[Proto.Byte.deserialize(stream).toInt()]
            packet.data = Proto.String.deserialize(stream)
            packet.skinType = Proto.Byte.deserialize(stream).toInt()
            packet.sceneName = Proto.String.deserialize(stream)

            return packet
        }
    }
}
