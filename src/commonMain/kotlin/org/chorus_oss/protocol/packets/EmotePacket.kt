package org.chorus_oss.protocol.packets


class EmotePacket : Packet(id) {
    var runtimeId: Long = 0
    var xuid: String = ""
    var platformId: String = ""
    var emoteID: String? = null
    var flags: Byte = 0
    var emoteDuration: Int = 0

    override fun pid(): Int {
        return ProtocolInfo.EMOTE_PACKET
    }

    override fun encode(byteBuf: ByteBuf) {
        byteBuf.writeActorRuntimeID(this.runtimeId)
        byteBuf.writeString(emoteID!!)
        byteBuf.writeUnsignedVarInt(this.emoteDuration)
        byteBuf.writeString(this.xuid)
        byteBuf.writeString(this.platformId)
        byteBuf.writeByte(flags.toInt())
    }



    companion object : PacketCodec<EmotePacket> {
        override fun deserialize(stream: Source): EmotePacket {
            val packet = EmotePacket()

            packet.runtimeId = byteBuf.readActorRuntimeID()
            packet.emoteID = Proto.String.deserialize(stream)
            packet.emoteDuration = byteBuf.readUnsignedVarInt()
            packet.xuid = Proto.String.deserialize(stream)
            packet.platformId = Proto.String.deserialize(stream)
            packet.flags = Proto.Byte.deserialize(stream)

            return packet
        }
    }
}
